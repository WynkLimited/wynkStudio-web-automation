package in.wynk.framework;

import in.wynk.common.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PostAction extends Driver {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    if (System.getProperty("JOB_NAME") != null && !System.getProperty("JOB_NAME").trim()
        .equalsIgnoreCase("")) {
      Utils utils = new Utils(null);
      HashMapNew env = utils.getEnvValues();
      String environment = System.getProperty("env") != null && !System.getProperty("env").trim()
          .equalsIgnoreCase("") ? System.getProperty("env").trim() : env.get("env").trim();
      if (!environment.trim().equalsIgnoreCase(env.get("env").trim())) {
        env = utils.getEnvValues(environment);
      }
      utils = new Utils(env);
      File rerun = new File("target/rerun.txt");
      if (rerun.exists() && rerun.length() > 0) {
        utils.uploadToS3("rerun/" + System.getProperty("JOB_NAME").trim() + "/index.txt",
            System.getProperty("user.dir") + "/target/rerun.txt", "text/plain");
      }
    }
  }
}
