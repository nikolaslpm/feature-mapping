package com.nklpm.annotation.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nklpm.annotation.Bug;
import com.nklpm.annotation.Link;
import com.nklpm.annotation.Scenario;
import com.nklpm.annotation.Steps;
import com.nklpm.report.Feature;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FeatureMapProcessor {

    public void initializeObject(Object... object) throws IllegalArgumentException, IOException {

        com.nklpm.report.Epic epic = new com.nklpm.report.Epic();
        List<Feature> features = new ArrayList<>();

        for (Object o : object) {
            Class<?> clazz;
            if (o instanceof Class) {
                clazz = (Class) o;
            } else {
                clazz = o.getClass();
            }
            Feature feature = new Feature();

            if (clazz.isAnnotationPresent(com.nklpm.annotation.Epic.class)) {
                String valueClass = clazz.getAnnotation(com.nklpm.annotation.Epic.class).value();
                epic.setEpicName(valueClass);
            }

            if (clazz.isAnnotationPresent(com.nklpm.annotation.Link.class)) {
                String valueClass = clazz.getAnnotation(com.nklpm.annotation.Link.class).value();
                epic.setLink(valueClass);
            }

            if (clazz.isAnnotationPresent(com.nklpm.annotation.Feature.class)) {
                String valueClass = clazz.getAnnotation(com.nklpm.annotation.Feature.class).value();
                feature.setFeature(valueClass);
            }

            int tests = 0;
            List<com.nklpm.report.Scenario> scenarioList = new ArrayList<>();

            for (Method method : clazz.getDeclaredMethods()) {
                com.nklpm.report.Scenario scenario = new com.nklpm.report.Scenario();

                if (method.isAnnotationPresent(Test.class)) {
                    tests++;
                }
                if (method.isAnnotationPresent(Scenario.class)) {
                    String value = method.getAnnotation(Scenario.class).value();
                    scenario.setScenarioName(value);
                }
                if (method.isAnnotationPresent(Steps.class)) {
                    String value = method.getAnnotation(Steps.class).value();
                    scenario.setStep(value);
                }
                if (method.isAnnotationPresent(Link.class)) {
                    String value = method.getAnnotation(Link.class).value();
                    scenario.setLink(value);
                }
                if (method.isAnnotationPresent(Bug.class)) {
                    String value = method.getAnnotation(Bug.class).value();
                    scenario.setBug(value);
                }
                scenarioList.add(scenario);
            }
            feature.setScenarios(scenarioList);
            feature.setTestCount(tests);
            features.add(feature);
            epic.setFeatures(features);
        }

        int featureTestCount = features.stream()
            .map(Feature::getTestCount)
            .reduce(0, Integer::sum);
        System.out.println("Features Test Count: " + featureTestCount);
        epic.setTotalTestCount(featureTestCount);
        epic.setFeatureCount(features.size());

        System.out.println(epic);
        new ObjectMapper().writeValue(new File("target/feature-report.json"), epic);
    }
}
