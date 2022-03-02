package in.wynk.framework;

import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {

  public CustomPicoFactory() {
  }

  @Override
  public void stop() {
    //Do Nothing
  }
}