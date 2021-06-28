package com.nklpm.report;

import java.util.List;

public class Epic {

    private String epicName;
    private String link;
    private List<Feature> features;
    private int featureCount;
    private int totalTestCount;

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(int featureCount) {
        this.featureCount = featureCount;
    }

    public int getTotalTestCount() {
        return totalTestCount;
    }

    public void setTotalTestCount(int totalTestCount) {
        this.totalTestCount = totalTestCount;
    }

    @Override
    public String toString() {
        return "Epic{" +
            "epicName='" + epicName + '\'' +
            ", link='" + link + '\'' +
            ", features=" + features +
            ", featureCount=" + featureCount +
            ", featureTestCount=" + totalTestCount +
            '}';
    }
}
