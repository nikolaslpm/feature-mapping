package com.nklpm.report;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class JunitTestDiscovery {

    public List<String> discoverTests() {
        String packageName =
            Arrays.stream(Objects.requireNonNull(new File("src/test/java").listFiles(File::isDirectory)))
                .findFirst()
                .get()
                .getName();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
////                selectPackage("com.nklpm.template.example")
//                selectPackage("com")
                selectPackage(packageName)
            )
            .filters(
                includeClassNamePatterns(".*Test", ".*IT", ".*CT")
            )
            .build();

        Launcher launcher = LauncherFactory.create();

        TestPlan testPlan = launcher.discover(request);
        return testPlan.getChildren("[engine:junit-jupiter]")
            .stream().map(TestIdentifier::getLegacyReportingName).collect(Collectors.toList());
    }
}
