package com.nklpm.template.example;

import com.nklpm.annotation.Bug;
import com.nklpm.annotation.Epic;
import com.nklpm.annotation.Feature;
import com.nklpm.annotation.Scenario;
import com.nklpm.annotation.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic(value = "My Epic")
@Feature("My Feature")
public class Dummy2Test {

    @Scenario("Another Test")
    @Steps("Given some condition Then true")
    @Bug("ISSUE-1234")
    @Test
    void test1(){
        Assertions.assertTrue(true);
    }

    @Scenario("Another Second Test")
    @Steps("Should throw exception when some condition")
    @Test
    void test2(){
        Assertions.assertTrue(true);
    }

    @Scenario("Another Third Test")
    @Steps("When some condition Then true")
    @Test
    void test3(){
        Assertions.assertTrue(true);
    }

    @Scenario("Another Fourth Test")
    @Steps("Given some condition When its true Then success")
    @Bug("ISSUE-1234, ISSUE-1234, ISSUE-1234")
    @Test
    void test4(){
        Assertions.assertTrue(true);
    }

    @Scenario("Another Fifth Test")
    @Steps("Given some condition When its true Then success")
    @Bug("ISSUE-1234, ISSUE-1234, ISSUE-1234")
    @Test
    void test5(){
        Assertions.assertTrue(true);
    }

    @Scenario("Another Sixth Test")
    @Steps("Given some condition When its true Then success")
    @Bug("ISSUE-1234, ISSUE-1234, ISSUE-1234")
    @Test
    void test6(){
        Assertions.assertTrue(true);
    }

}
