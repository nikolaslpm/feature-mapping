package com.nklpm.core;

import java.util.Optional;

public enum TestLevel {

    UT("Test"),
    IT("IT"),
    CT("CT");

    private String suffix;

    TestLevel(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public static Optional<TestLevel> getTestLevel(String className){
        if (className.endsWith("Test")) {
            return Optional.of(UT);
        }
        if (className.endsWith("IT")) {
            return Optional.of(IT);
        }
        if (className.endsWith("CT")) {
            return Optional.of(CT);
        }
        return Optional.empty();
    }
}
