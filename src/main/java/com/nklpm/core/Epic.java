package com.nklpm.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("epic")
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use= JsonTypeInfo.Id.NAME)
public class Epic extends BaseDescription {

    private Feature feature;

    public Epic(Feature feature) {
        this.feature = feature;
    }

    public Epic(String name, String link, String description, Feature feature) {
        super(name, link, description);
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
