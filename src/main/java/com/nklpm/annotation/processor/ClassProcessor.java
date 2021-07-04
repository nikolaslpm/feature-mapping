package com.nklpm.annotation.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nklpm.core.Epic;
import com.nklpm.core.Feature;
import com.nklpm.core.Scenario;
import com.nklpm.report.Report;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassProcessor {

    public void initializeObject(Object... object) throws IllegalArgumentException, IOException {
        List<Epic> epicList = new ArrayList<>();

        for (Object o : object) {
            Class<?> clazz = o instanceof Class ? (Class) o : o.getClass();

            Feature feature;
            List<com.nklpm.core.Scenario> scenarioList = new ArrayList<>();

            for (Method method : clazz.getDeclaredMethods()) {
                Scenario scenario;

                if (method.isAnnotationPresent(Test.class)) {
                    scenario =
                        mapToScenario(method.getAnnotation(com.nklpm.annotation.Scenario.class), method.getName());
                    scenarioList.add(scenario);
                }
            }

            String testLevel = getTestLevel(clazz.getSimpleName());
            String classPath = clazz.getName();
            feature = mapToFeature(
                clazz.getAnnotation(com.nklpm.annotation.Feature.class),
                scenarioList,
                testLevel,
                classPath
            );
            epicList.add(mapToEpic(clazz.getAnnotation(com.nklpm.annotation.Epic.class), feature));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/feature-report.json"), new Report(epicList));
    }

    public Epic mapToEpic(com.nklpm.annotation.Epic epicAnnotation, Feature feature) {
        if (epicAnnotation != null) {
            return new Epic(epicAnnotation.value(), epicAnnotation.link(), epicAnnotation.description(), feature);
        }
        return new Epic(feature);
    }

    public Feature mapToFeature(
        com.nklpm.annotation.Feature featureAnnotation,
        List<Scenario> scenarioList,
        String testLevel,
        String classPath
    ) {
        if (featureAnnotation != null) {
            return new Feature(
                featureAnnotation.value(),
                featureAnnotation.link(),
                featureAnnotation.description(),
                scenarioList,
                testLevel,
                classPath
            );
        }
        return new Feature(scenarioList, testLevel, classPath);
    }

    public Scenario mapToScenario(com.nklpm.annotation.Scenario scenarioAnnotation, String testName) {
        if (scenarioAnnotation != null) {
            return new Scenario(
                scenarioAnnotation.value(),
                scenarioAnnotation.link(),
                scenarioAnnotation.description(),
                scenarioAnnotation.steps(),
                testName
            );
        }
        return new Scenario(testName);
    }

    public String getTestLevel(String className) {
        if (className.contains("Test")) {
            return "UT";
        }
        if (className.contains("IT")) {
            return "IT";
        }
        if (className.contains("CT")) {
            return "CT";
        }
        return "UNKNOWN";
    }
}
