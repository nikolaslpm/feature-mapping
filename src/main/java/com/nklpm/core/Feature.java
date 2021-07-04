package com.nklpm.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Feature extends BaseDescription {

    private List<Scenario> scenarioList;
    private String testLevel;
    private String classPath;

    public Feature(List<Scenario> scenarioList, String testLevel, String classPath) {
        this.scenarioList = scenarioList;
        this.testLevel = testLevel;
        this.classPath = classPath;
        super.setName(classPath);
    }

    public Feature(
        String name,
        String link,
        String description,
        List<Scenario> scenarioList,
        String testLevel,
        String classPath
    ) {
        super(name.isEmpty() ? classPath : name, link, description);
        this.scenarioList = scenarioList;
        this.testLevel = testLevel;
        this.classPath = classPath;
    }

    public List<Scenario> getScenarioList() {
        return scenarioList;
    }

    public void setScenarioList(List<Scenario> scenarioList) {
        this.scenarioList = scenarioList;
    }

    public String getTestLevel() {
        return testLevel;
    }

    public void setTestLevel(String testLevel) {
        this.testLevel = testLevel;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @JsonProperty("scenarioCount")
    public int getScenarioCount() {
        return scenarioList.size();
    }
}
