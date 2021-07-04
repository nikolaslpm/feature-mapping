package com.nklpm.template.example;

import com.nklpm.annotation.Bug;
import com.nklpm.annotation.Epic;
import com.nklpm.annotation.Feature;
import com.nklpm.annotation.Scenario;
import com.nklpm.annotation.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic(value = "My Epic", description = "My Epic description", link = "E-123") //value = Epic Factory?
@Feature(value = "My Feature", description = "My feature description", link = "F-222", epicName = "E-123")
public class DummyTest {

    @Scenario(value = "First Test", description = "Scenario description", link = "T-333", steps = {"Given something", "When action", "Then should do something"})
    @Steps("Given some condition Then true")
    @Bug("ISSUE-1234")
    @Test
    void test1(){
        Assertions.assertTrue(true);
    }

    @Scenario("Second Test")
    @Steps("Should throw exception when some condition")
    @Test
    void test2(){
        Assertions.assertTrue(true);
    }

    @Scenario("Third Test")
    @Steps("When some condition Then true")
    @Test
    void test3(){
        Assertions.assertTrue(true);
    }

    @Scenario("First Test")
    @Steps("Given some condition When its true Then success")
    @Bug("ISSUE-1234, ISSUE-1234, ISSUE-1234")
    @Test
    void test4(){
        Assertions.assertTrue(true);
    }

}
