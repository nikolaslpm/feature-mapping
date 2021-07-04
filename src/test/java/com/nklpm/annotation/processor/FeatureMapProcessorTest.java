package com.nklpm.annotation.processor;

import com.nklpm.report.JunitTestDiscovery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class FeatureMapProcessorTest {

    @Test
    void test() throws IOException {
        List<String> testList = new JunitTestDiscovery().discoverTests();
        Object[] objects = testList.stream()
            .map(classpath -> {
                try {
                    return Class.forName(classpath);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }).toArray();
        new ClassProcessor().initializeObject(objects);

//        Arrays.stream(Objects.requireNonNull(new File("src/test/java").listFiles(File::isDirectory))).findFirst().get();
    }
}