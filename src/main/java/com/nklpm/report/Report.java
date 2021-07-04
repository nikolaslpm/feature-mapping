package com.nklpm.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nklpm.core.Epic;
import com.nklpm.core.Feature;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class Report {

    private List<Epic> epicList;

    public Report(List<Epic> epicList) {
        this.epicList = epicList;
    }

    public List<Epic> getEpicList() {
        return epicList;
    }

    public void setEpicList(List<Epic> epicList) {
        this.epicList = epicList;
    }

    @JsonProperty("epicCount")
    public long getEpicCount() {
        return epicList.stream().filter(epic -> epic.getName() != null).count();
    }

    @JsonProperty("featureCount")
    public int getFeatureCount() {
        return epicList.size();
    }

    @JsonProperty("scenarioCount")
    public long getScenarioCount() {
        return epicList.stream().map(Epic::getFeature).map(Feature::getScenarioCount).reduce(0, Integer::sum);
    }

    @JsonProperty("testLevelCount")
    public TestLevelReport getTestLevelReport() {
//        int total = epicList.stream()
//            .map(Epic::getFeature)
//            .filter(feature -> feature.getTestLevel().contains("UT"))
//            .mapToInt(Feature::getScenarioCount).sum();
        TestLevelReport testLevelReport = new TestLevelReport();
        epicList.stream()
            .map(Epic::getFeature)
            .forEach(testLevelReport::generate);
        return testLevelReport;

    }
}
