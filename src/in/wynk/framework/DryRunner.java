package in.wynk.framework;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import org.testng.annotations.Test;

@CucumberOptions(plugin = "json:target/cucumber-report/cucumber-report-feature-composite.json", features = {
    "user-files/features"}, monochrome = true)
public class DryRunner extends Driver {

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
