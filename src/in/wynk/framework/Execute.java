package in.wynk.framework;

import in.wynk.common.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Execute extends Driver {

  public static void main(String[] args) throws Exception {
    Utils utils = new Utils(null);
    HashMapNew env = utils.getEnvValues();
    String environment =
        System.getProperty("env") != null && !System.getProperty("env").trim().equalsIgnoreCase("")
            ? System.getProperty("env").trim() : env.get("env").trim();
    if (!environment.trim().equalsIgnoreCase(env.get("env").trim())) {
      env = utils.getEnvValues(environment);
    }
    String browser = System.getProperty("browser") != null && !System.getProperty("browser").trim()
        .equalsIgnoreCase("") ? System.getProperty("browser").trim() : env.get("browser");
    boolean android = browser.trim().equalsIgnoreCase("ANDROID");
    boolean ios = browser.trim().equalsIgnoreCase("IOS");
    utils = new Utils(env);
    updateRunFailedScenariosConfig(env);
    int count = reserveDevicesAndReturnCount(env, android, ios, utils, browser);
    createAccountsForCount(env, count, browser, environment, utils);
    generateSuiteXmlFileAndRun(browser, count, env);
  }

  private static int reserveDevicesAndReturnCount(HashMapNew env, boolean android, boolean ios,
      Utils utils, String browser) throws Exception {
    int androidDeviceCount = 1, iosDeviceCount = 1;
    int count = !env.get("maxThreads").trim().equalsIgnoreCase("") ? Integer
        .valueOf(env.get("maxThreads").trim()) : 1;
    if ((android || ios) && !env.get("devicesConfiguration").trim().equalsIgnoreCase("")) {
      count = 1;
    }
    boolean devicefarm = Boolean.valueOf(env.get("devicefarm"));
    boolean includeBusyDevices = Boolean.valueOf(env.get("includeBusyDevices"));
    boolean includeOtherUserBusyDevices = Boolean.valueOf(env.get("includeOtherUserBusyDevices"));
    String reservationKey = env.get("reservationKey").trim();
    String wifiName = env.get("wifiName").trim();
    if (android) {
      if (!env.get("deviceId").trim().equalsIgnoreCase("")) {
        String[] androidDeviceUids = env.get("deviceId").trim().replace(";", ",").split(",");
        int actualAndroidDeviceCount = 0;
        for (String androidDeviceUid : androidDeviceUids) {
          if (devicefarm) {
            boolean success = utils.reserveParticularDevice(androidDeviceUid, browser, includeBusyDevices, wifiName, false, includeOtherUserBusyDevices, reservationKey);
            if (!success) {
              continue;
            }
          }
          actualAndroidDeviceCount++;
        }
        androidDeviceCount = actualAndroidDeviceCount;
      } else if (env.get("autoDetectAndroidDevices").trim().equalsIgnoreCase("true") || env
          .get("autoDetectAndroidDevices").trim().equalsIgnoreCase("y") || env
          .get("autoDetectAndroidDevices").trim().equalsIgnoreCase("yes")) {
        boolean restartAdbOnDemand = false;
        if(!env.get("restartAdbOnDemand").trim().equalsIgnoreCase(""))
          restartAdbOnDemand = Boolean.valueOf(env.get("restartAdbOnDemand").trim());
        List<String> androidDeviceUids = utils.getAvailableDevices(browser, includeBusyDevices, false,
                count, wifiName, includeOtherUserBusyDevices, restartAdbOnDemand, reservationKey);
        androidDeviceCount = androidDeviceUids.size();
      }
    } else if (ios) {
      if (!env.get("deviceId").trim().equalsIgnoreCase("")) {
        String[] iosDeviceUids = env.get("deviceId").trim().replace(";", ",").split(",");
        int actualIOSDeviceCount = 0;
        for (String iosDeviceUid : iosDeviceUids) {
          if (devicefarm) {
            boolean success = utils.reserveParticularDevice(iosDeviceUid, browser, includeBusyDevices, wifiName, false, includeOtherUserBusyDevices, reservationKey);
            if (!success) {
              continue;
            }
          }
          actualIOSDeviceCount++;
        }
        iosDeviceCount = actualIOSDeviceCount;
      } else if (env.get("autoDetectIOSDevices").trim().equalsIgnoreCase("true") || env
          .get("autoDetectIOSDevices").trim().equalsIgnoreCase("y") || env
          .get("autoDetectIOSDevices").trim().equalsIgnoreCase("yes")) {
        List<String> iosDeviceUids = utils.getAvailableDevices(browser, includeBusyDevices, false, count, wifiName, includeOtherUserBusyDevices, false, reservationKey);
        iosDeviceCount = iosDeviceUids.size();
      }
    }
    if (android) {
      count =
          androidDeviceCount > 0 ? (androidDeviceCount < count ? androidDeviceCount : count) : 1;
    } else if (ios) {
      count = iosDeviceCount > 0 ? (iosDeviceCount < count ? iosDeviceCount : count) : 1;
    }
    return count;
  }

  private static void createAccountsForCount(HashMapNew env, int count, String browser,
      String environment, Utils utils) throws Exception {
    CreateAccounts create = new CreateAccounts(env);
    String country_code =
        System.getProperty("country_code") != null && !System.getProperty("country_code").trim()
            .equalsIgnoreCase("") ? System.getProperty("country_code").trim()
            : env.get("country_code");
    boolean createAccount =
        System.getProperty("createAccount") != null && !System.getProperty("createAccount").trim()
            .equalsIgnoreCase("") ? Boolean.valueOf(System.getProperty("createAccount").trim())
            : Boolean.valueOf(env.get("createAccount"));
    String configDataPath = env.get("appCredentialsPath").trim();
    configDataPath =
        configDataPath.substring(0, configDataPath.lastIndexOf("_")) + "_" + environment
            .toUpperCase() + ".xml";
    String[] userTypes = new String[]{"SOCIAL_FREE_EMAIL", "SOCIAL_VIP_EMAIL",
        "SOCIAL_PREMIUM_EMAIL", "SOCIAL_FREE_PHONE_NUMBER", "SOCIAL_VIP_PHONE_NUMBER",
        "SOCIAL_PREMIUM_PHONE_NUMBER"};
    HashMapNew mapNewUsersWithExistingKeys = new HashMapNew();
    mapNewUsersWithExistingKeys.put("SOCIAL_PREMIUM_EMAIL",
        "EMAIL_ADDRESS,PASSWORD;PREMIUM_EMAIL_ADDRESS,PREMIUM_PASSWORD");
    mapNewUsersWithExistingKeys.put("SOCIAL_VIP_EMAIL", "SPORTS_EMAIL_ADDRESS,SPORTS_PASSWORD");
    String platform = "android";
    if (createAccount) {
      for (int i = 0; i < count; i++) {
//				HashMapNew data = create.create(browser.trim().toUpperCase() + String.valueOf(i + 1), platform, country_code, userTypes, mapNewUsersWithExistingKeys);
//				saveAppCredentialsXmlFile(configDataPath, data, environment, browser.trim().toUpperCase() + String.valueOf(i + 1));
      }
    }
  }

  private static void generateSuiteXmlFileAndRun(String browser, int count, HashMapNew env)
      throws IOException {
    XmlSuite suite = new XmlSuite();
    suite.setName("Suite");
    XmlTest test = new XmlTest(suite);
    test.setName(browser.trim().toUpperCase());
    test.setGroupByInstances(true);
    Map<String, String> parameters = new HashMap<String, String>();
    parameters.put("browser", browser.trim().toUpperCase());
    test.setParameters(parameters);
    List<XmlClass> allclasses = new ArrayList<XmlClass>();
    XmlClass testClass = new XmlClass();
    String testNGSuiteFileName = "testng-customsuite.xml";
    testClass.setName("in.wynk.framework.Runner");
    allclasses.add(testClass);
    test.setXmlClasses(Arrays.asList(allclasses.toArray(new XmlClass[allclasses.size()])));
    suite.setDataProviderThreadCount(count);
    System.out.println(suite.toXml());
    FileOutputStream fout = new FileOutputStream(
        System.getProperty("user.dir") + OSValidator.delimiter + "user-files"
            + OSValidator.delimiter + "testng" + OSValidator.delimiter + testNGSuiteFileName,
        false);
    new PrintStream(fout).println(suite.toXml());
    fout.close();
    List<XmlSuite> suites = new ArrayList<XmlSuite>();
    suites.add(suite);
    boolean writeToFileOnly =
        System.getProperty("writeToFile") != null && !System.getProperty("writeToFile").trim()
            .equalsIgnoreCase("") ? Boolean.valueOf(System.getProperty("writeToFile").trim())
            : Boolean.valueOf(env.get("writeToFile").trim());
    if (!writeToFileOnly) {
      TestNG tng = new TestNG();
      tng.setXmlSuites(suites);
      tng.run();
    }
  }

  private static void saveAppCredentialsXmlFile(String configDataPath, HashMapNew appCredentials, String env, String driverType) {
    String RootPath = System.getProperty("user.dir");
    try {
      String xmlPath = RootPath + configDataPath;
      if (!new File(xmlPath).exists()) {
        xmlPath = RootPath + configDataPath;
      }
      File fXmlFile = new File(xmlPath);
      if (!fXmlFile.exists()) {
        fXmlFile.createNewFile();
      }
      DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbFac.newDocumentBuilder();
      Document xmldoc;
      try {
        xmldoc = docBuilder.parse(fXmlFile);
      } catch (Exception ex) {
        xmldoc = docBuilder.newDocument();
      }
      Node ENV = xmldoc.getElementsByTagName("ENV").item(0);
      if (ENV == null) {
        Element _env = xmldoc.createElement("ENV");
        ENV = xmldoc.appendChild(_env);
      }
      XPathFactory xPathfac = XPathFactory.newInstance();
      XPath xpath = xPathfac.newXPath();
      XPathExpression expr = xpath.compile("//" + env.trim().toUpperCase());
      Object obj = expr.evaluate(xmldoc, XPathConstants.NODESET);
      if (obj != null) {
        Node node = ((NodeList) obj).item(0);
        if (node == null) {
          Element envNode = xmldoc.createElement(env.trim().toUpperCase());
          ENV.appendChild(envNode);
        }
      }
      expr = xpath.compile("//" + env.trim().toUpperCase() + "/" + driverType.trim().toUpperCase());
      obj = expr.evaluate(xmldoc, XPathConstants.NODESET);
      if (obj != null) {
        Node node = ((NodeList) obj).item(0);
        if (node != null) {
          NodeList nl = node.getChildNodes();
          for (int child = 0; child < nl.getLength(); child++) {
            String nodeName = nl.item(child).getNodeName().trim();
            if (!appCredentials.get(nodeName).trim().equalsIgnoreCase("")) {
              nl.item(child).setTextContent(appCredentials.get(nodeName));
              appCredentials.remove(nodeName);
            }
          }
          java.util.Iterator<String> iter = appCredentials.keySet().iterator();
          while (iter.hasNext()) {
            String key = iter.next();
            Element elem = xmldoc.createElement(key);
            elem.appendChild(xmldoc.createTextNode(appCredentials.get(key)));
            node.appendChild(elem);
          }
        } else {
          expr = xpath.compile("//" + env.trim().toUpperCase());
          obj = expr.evaluate(xmldoc, XPathConstants.NODESET);
          node = ((NodeList) obj).item(0);
          Element driverTypeNode = xmldoc.createElement(driverType.trim().toUpperCase());
          java.util.Iterator<String> iter = appCredentials.keySet().iterator();
          while (iter.hasNext()) {
            String key = iter.next();
            Element elem = xmldoc.createElement(key);
            elem.appendChild(xmldoc.createTextNode(appCredentials.get(key)));
            driverTypeNode.appendChild(elem);
          }
          node.appendChild(driverTypeNode);
        }
      }
      //write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(xmldoc);
      String resultXmlPath = RootPath + configDataPath;
      System.out.println("App credentials path : " + resultXmlPath);
      StreamResult result = new StreamResult(new FileOutputStream(resultXmlPath));
      transformer.transform(source, result);
    } catch (Exception excep) {
      excep.printStackTrace();
    }
  }

  private static void updateRunFailedScenariosConfig(HashMapNew env) throws Exception {
    boolean runFailedScenarios = System.getProperty("runFailedScenarios") != null && !System
        .getProperty("runFailedScenarios").trim().equalsIgnoreCase("") ? Boolean
        .valueOf(System.getProperty("runFailedScenarios").trim())
        : Boolean.valueOf(env.get("runFailedScenarios").trim());
    if (runFailedScenarios) {
      if (System.getProperty("JOB_NAME") != null && !System.getProperty("JOB_NAME").trim()
          .equalsIgnoreCase("")) {
        Utils utils = new Utils(env);
        utils.downloadFromS3("rerun/" + System.getProperty("JOB_NAME").trim() + "/index.txt",
            System.getProperty("user.dir") + "/target/rerun.txt", false);
      }
      File rerun = new File("target/rerun.txt");
      if (rerun.exists() && rerun.length() > 0) {
        FileUtils.copyFile(new File("target/rerun.txt"), new File("user-files/testng/rerun.txt"));
      } else {
        System.out.println("No Failed Scenarios Found");
        throw new Exception("No Failed Scenarios Found");
      }
    }
  }
}