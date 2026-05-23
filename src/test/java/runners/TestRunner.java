package runners;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {

        TestNG testng = new TestNG();

        // Point to testng.xml
        List<String> suites = new ArrayList<>();
        suites.add("testng.xml");

        testng.setTestSuites(suites);
        testng.run();
    }
}