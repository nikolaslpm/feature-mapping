package com.nklpm.report;

import com.nklpm.core.Feature;

public class TestLevelReport {

    private long utCount;
    private long itCount;
    private long ctCount;

    public long getUtCount() {
        return utCount;
    }

    public void setUtCount(long utCount) {
        this.utCount = utCount;
    }

    public long getItCount() {
        return itCount;
    }

    public void setItCount(long itCount) {
        this.itCount = itCount;
    }

    public long getCtCount() {
        return ctCount;
    }

    public void setCtCount(long ctCount) {
        this.ctCount = ctCount;
    }

    public void generate(Feature feature) {
        if (feature.getTestLevel().contains("UT")) {
            this.utCount += feature.getScenarioCount();
        } else if (feature.getTestLevel().contains("IT")) {
            this.itCount += feature.getScenarioCount();
        } else if (feature.getTestLevel().contains("CT")) {
            this.ctCount += feature.getScenarioCount();
        }
    }
}
