package com.capgemini;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.service.ServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ServiceTestSuite.class})
@SpringBootTest(properties = "spring.profiles.active=test")
public class AllTests {

}
