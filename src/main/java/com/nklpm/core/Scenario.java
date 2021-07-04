package com.nklpm.core;

public class Scenario extends BaseDescription {

    private String[] steps;
    private Feature feature;
    public Scenario(String name, String link, String description, String[] steps, Feature feature) {
        super(name, link, description);
        this.steps = steps;
        this.feature = feature;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
