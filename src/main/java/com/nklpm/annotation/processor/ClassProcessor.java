package com.nklpm.annotation.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nklpm.core.Epic;
import com.nklpm.core.Feature;
import com.nklpm.core.Scenario;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassProcessor {

    public void initializeObject(Object... object) throws IllegalArgumentException, IOException {

        List<Feature> features = new ArrayList<>();
        List<com.nklpm.core.Scenario> scenarioList = new ArrayList<>();

        for (Object o : object) {
            Class<?> clazz;
            if (o instanceof Class) {
                clazz = (Class) o;
            } else {
                clazz = o.getClass();
            }
            Feature feature = null;
            Epic epic = null;

            if (clazz.isAnnotationPresent(com.nklpm.annotation.Epic.class)) {
                epic = mapToEpic(clazz.getAnnotation(com.nklpm.annotation.Epic.class));
            }

            if (clazz.isAnnotationPresent(com.nklpm.annotation.Feature.class)) {
                String testLevel = getTestLevel(clazz.getSimpleName());
                String classPath = clazz.getName();
                feature = mapToFeature(clazz.getAnnotation(com.nklpm.annotation.Feature.class), epic, testLevel, classPath);
            }

            int tests = 0;
//            List<com.nklpm.core.Scenario> scenarioList = new ArrayList<>();

            for (Method method : clazz.getDeclaredMethods()) {
                Scenario scenario = null;

                if (method.isAnnotationPresent(Test.class)) {
                    tests++;
                }
                if (method.isAnnotationPresent(com.nklpm.annotation.Scenario.class)) {
                    scenario = mapToScenario(method.getAnnotation(com.nklpm.annotation.Scenario.class), feature);
                }
//                if (method.isAnnotationPresent(Steps.class)) {
//                    String value = method.getAnnotation(Steps.class).value();
//                    scenario.setStep(value);
//                }
//                if (method.isAnnotationPresent(Bug.class)) {
//                    String value = method.getAnnotation(Bug.class).value();
//                    scenario.setBug(value);
//                }
                scenarioList.add(scenario);
            }
//            feature.setScenarios(scenarioList);
//            feature.setTestCount(tests);
//            features.add(feature);
//            epic.setFeatures(features);
        }

//        int featureTestCount = features.stream()
//            .map(Feature::getTestCount)
//            .reduce(0, Integer::sum);
//        System.out.println("Features Test Count: " + featureTestCount);
//        epic.setTotalTestCount(featureTestCount);
//        epic.setFeatureCount(features.size());
//
//        System.out.println(epic);
//        new ObjectMapper().writeValue(new File("target/feature-report.json"), epic);
        new ObjectMapper().writeValue(new File("target/feature-report-v0.json"), scenarioList);
    }

    public Epic mapToEpic(com.nklpm.annotation.Epic epicAnnotation){
        return new Epic(epicAnnotation.value(), epicAnnotation.description(), epicAnnotation.link());
    }

    public Feature mapToFeature(
        com.nklpm.annotation.Feature featureAnnotation,
        Epic epic,
        String testLevel,
        String classPath
    ) {
        return new Feature(
            featureAnnotation.value(),
            featureAnnotation.link(),
            featureAnnotation.description(),
            epic,
            testLevel,
            classPath
        );
    }

    public Scenario mapToScenario(com.nklpm.annotation.Scenario scenarioAnnotation, Feature feature){
        return new Scenario(
            scenarioAnnotation.value(),
            scenarioAnnotation.link(),
            scenarioAnnotation.description(),
            scenarioAnnotation.steps(),
            feature
        );
    }

    public String getTestLevel(String className) {
        if(className.contains("Test")){
            return "UT";
        }
        if(className.contains("IT")) {
            return "IT";
        }
        if(className.contains("CT")){
            return "CT";
        }
        return "UNKNOWN";
    }
}
