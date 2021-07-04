package com.nklpm.core;

public class Feature extends BaseDescription {

    private Epic epic;
    private String testLevel;
    private String classPath;

    public Feature(
        String name,
        String link,
        String description,
        Epic epic,
        String testLevel,
        String classPath
    ) {
        super(name, link, description);
        this.epic = epic;
        this.testLevel = testLevel;
        this.classPath = classPath;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
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
}
