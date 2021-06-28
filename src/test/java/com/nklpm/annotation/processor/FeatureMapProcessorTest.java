package com.nklpm.annotation.processor;

import com.nklpm.report.JunitTestDiscovery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class FeatureMapProcessorTest {

    @Test
    void test() throws InvocationTargetException, IllegalAccessException, IOException {
        List<String> testList = new JunitTestDiscovery().discoverTests();
        Object[] objects = testList.stream()
//            .filter(classpath -> classpath.contains("Dummy"))
            .map(classpath -> {
            try {
                return Class.forName(classpath);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).toArray();
//        new FeatureMapProcessor().initializeObject(new DummyTest(), new Dummy2Test());
        new FeatureMapProcessor().initializeObject(objects);
    }
}