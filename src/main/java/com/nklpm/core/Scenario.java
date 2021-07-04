package com.nklpm.core;

public class Scenario extends BaseDescription {

    private String[] steps;
    private String testName;

    public Scenario(String name) {
        super.setName(name);
        this.testName = name;
    }

    public Scenario(String name, String link, String description, String[] steps, String testName) {
        super(name.isEmpty() ? testName : name, link, description);
        this.steps = steps;
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
