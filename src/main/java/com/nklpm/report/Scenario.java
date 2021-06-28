package com.nklpm.report;

public class Scenario {

    private String scenarioName;
    private String step;
    private String link;
    private String bug;

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        return "Scenario{" +
            "scenarioName='" + scenarioName + '\'' +
            ", step='" + step + '\'' +
            ", link='" + link + '\'' +
            ", bug='" + bug + '\'' +
            '}';
    }
}
