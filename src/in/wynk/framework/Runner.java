package in.wynk.framework;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import org.testng.annotations.Test;

@CucumberOptions(plugin = {"json:target/cucumber-report/cucumber-report-feature-composite.json", "pretty", "rerun:target/rerun.txt"}, features = {"user-files/features"}, glue = {"in.wynk.steps"},
        tags = {"~@wip","@karishma","~@P2"}, monochrome = true, strict = true)

public class Runner extends Driver {

  @Test(dataProvider = "scenarios")
  public void runScenarios(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature,
      String driverType, int count, String tagName, String testName, String threadName,
      String attributes) throws Throwable {
    if (DriverFactory.getThrowable() != null) {
      Throwable throwable = DriverFactory.getThrowable();
      DriverFactory.setThrowable(null);
      throw throwable;
    }
    testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
  }
}
