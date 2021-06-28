package com.nklpm.report;

import java.util.List;

public class Feature {

    private String feature;
    private List<Scenario> scenarios;
    private int testCount;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    @Override
    public String toString() {
        return "Feature{" +
            "feature='" + feature + '\'' +
            ", scenarios=" + scenarios +
            ", testNumbers=" + testCount +
            '}';
    }
}
