package in.wynk.framework;

import in.wynk.common.ApiUtils;
import in.wynk.framework.Driver.HashMapNew;
import java.util.Random;
import java.util.UUID;

public class CreateAccounts {

  HashMapNew Environment;
  DriverFactory driverFactory;
  ApiUtils apiUtils;

  public CreateAccounts(HashMapNew Environment) {
    driverFactory = new DriverFactory();
    this.Environment =
        Environment == null || Environment.size() == 0 ? DriverFactory.getEnvironment()
            : Environment;
    apiUtils = new ApiUtils(null, null, null, null);
  }

  private String randomEmail() {
    String generatedString = UUID.randomUUID().toString();
    generatedString = generatedString.replaceAll("-", "");
    generatedString = generatedString.substring(0, Math.min(generatedString.length(), 10));
    String email = "hs" + generatedString + "@example.com";
    System.out.println(email);
    return email;
  }

  private long randomNumber() {
    long start = 7 + new Random().nextInt(9 - 7 + 1);
    long lastdigits = 100000000 + new Random().nextInt(999999999 - 100000000 + 1);
    long phone = start * 1000000000 + lastdigits;
    return phone;
  }
}
