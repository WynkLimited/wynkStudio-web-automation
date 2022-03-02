# Wynk Apps Automation Lab
**Test Tool:**  `Appium 7.3.0`
**Platform:**   `Devicefarm`

### Development Setup
- Create ~/.m2/settings.xml
- Insert below xml
    ```
    <settings>
        <servers>
            <server>
                <id>test-framework</id>
                <username>prateek.ladha_wynk.in</username>
                <password>prateek</password>
            </server>
        </servers>
    </settings>
    ```
- Save xml file.(enter ":wq!" in terminal)

### Directory Structure
```
+-- src
	+-- <Android Pages>
	+-- <Android steps>
	+-- <Framework Utilities>
		+-- CreateAccounts
		+-- CustomPicoFactory
		+-- DryRunner
		+-- Execute
		+-- Runner
		+-- Utils
	+-- <iOS Pages>
	+-- <iOS Steps>
	+-- Configuration.xml
	+-- cucumber.properties
	+-- Environments.xml
+-- Reports
	+-- <Suite_CurrentTimestamp>
		+-- Custom HTML Report
+-- user-files
	+-- APIRequest
		+-- <Payloads>
	+-- apps
		+-- <version>
			+-- <*.app>
			+-- <*.apk>
			+-- <*.ipa>
	+-- Data
		+-- Config_PROD.xml
	+-- docker_files
	+-- executables
		+-- <Shell files for launching appium servers>
	+-- features
		+-- <feature files>
	+-- testng
		+-- testng-customsuite.xml
+-- pom.xml
+- updateDevicefarmMap.sh
```

### Writing a new test
- You need to create files in 3 folders
	- **src/%android/ios pages package%** : This folder contains page files which will be have locators, locators action methods and app specific utilities.
	- **src/%android/ios steps package%** : This folder contains step definitions to be used in feature file.
	- **user-files/features/%pod_name%/%feature_name.feature%** -  Add your scenarios here. Please specify logical tags for each scenario and feature, e.g. @SignUp and if your scenario is not completed, add @wip tag so that it does not get picked up while others are executing tests.
- To ensure feature file syntax is correct, use this command :
	- **Android** `mvn clean verify -Pfailsafe -Dtestng-suite-xml="dry-run-testng-customsuite.xml" -Dcucumber.options="-g in.wynk.steps.android --dry-run" -DenvCheck=false`
	- **iOS** `mvn clean verify -Pfailsafe -Dtestng-suite-xml="dry-run-testng-customsuite.xml" -Dcucumber.options="-g in.wynk.steps.ios --dry-run" -DenvCheck=false`
- Enter your prerequisite data(required for running the test e.g. app credentials) in `Config_<env>.xml` file under  `user-files/data` folder. 

### Upload the app
- Create a folder in `user-files/apps` path with the name `<app_version>-<app_version_code>`, e.g. 1.23.0-12615
- Copy your app in the same folder
- Rename the app name with `airtelxstream` in case of both Android and iOS
- Upload same app in S3, use this command -<br/> `aws s3 cp --recursive $PWD/user-files/apps/1.23.0-12615  s3://airtel-tv-catchup-dev/1.23.0-12615/ --region ap-south-1 --profile dev`.<br/> Make sure `awscli` is installed and configured in your system with aws `bsbportal` account credentials and profile name `dev` is set.

### Running a test locally
- Connect to `N` number of devices(Android/iOS)
- Launch `N` appium servers(Android/iOS). Go to `user-files/executables` and start the servers.
	- Appium server port for Android starts with `4723`
	- Appium server port for iOS starts with `5555`
- Create device tags like `<android1>, <android2> ... <androidN>` (similarly in case of iOS) in `src/Configuration.xml` file with the same appium ports which you are using while launching appium servers and different device configuration, (wdaLocalPort, webkitDebugProxyPort)(in case of iOS) and (systemPort, chromeDriverPort)(in case of Android) 
- Set the env, browser, appVersion, appName, maxThreads in src/Configuration.xml
- Set tags in Runner.java
- Run Execute.java
- Run testng-customsuite.xml
- After test completes, you can verify the reports stored in `Reports` folder. Same reports path is displayed in the console also.
	
**Make sure you have set right permissions in devices like MI, OPPO etc. for installing apps over USB from unknown sources**

### Running a test on Devicefarm
- Create device tags like `<android1>, <android2> ... <androidN>` (similarly in case of iOS) in `src/Configuration.xml` file with different device configuration, (wdaLocalPort, webkitDebugProxyPort)(in case of iOS) and (systemPort, chromeDriverPort)(in case of Android)
- Set the env, browser, appVersion, appName, maxThreads, deviceFarm in src/Configuration.xml
- Set tags in Runner.java
- Run Execute.java
- Run testng-customsuite.xml

### Running a test on proxy locally
- Open the postman collection - MitmProxy
- Start proxy on random 4-digit port number. For simplication, we use the series starting from `6001` for android devices and `7001` for ios devices.
- However before starting proxy, verify the port is not already open and in-use by other device. To check this, call `Is Port Open` api and verify the http status code - 404.
- Once the proxy is started, verify it by calling `Is Port Open` api and verify the http status code - 200.
- Configure your local device with the same port number.
- Open Wifi Settings -> Long press on the selected wifi -> Click on modify network -> Go to Advanced Options -> Select proxy `Manual` -> Enter proxy hostname `<proxyAddress>` -> Enter proxy port for which you have just made proxy open -> Click on save
- Open http://mitm.it on browser
- Download mitmproxy certifcate for respective device - ANDROID or IOS
- Install the certifcate with name `wynk`
- Now open the airtelxstream app -> Go to homepage
- Homepage should be loading with the content. It means proxy is working. ***Note: Proxy works only with prod debug app.***
- Configure same device udid and port number in src/DevicefarmProxyPortMap.json
- Set `runOnProxy : true` in src/Configuration.xml
- Set the `filter` in src/Configuration.xml. In case of multiple filters, separate it by comma(,) delimiter.
- Run your test
- Verify network calls are getting captured. Call `Get Network Logs` api and verify the response.

### Assert on network calls
- Call `in.wynk.common.Utils` `List<Entry> getFilteredNetworkLogs(Predicate<? super Entry> predicate, String browser, String udid, String port)` method.
- Enter you filter by using predicates and lambda expressions.
    ```
    String udid = sTestDetails.get().get("udid").trim();
	String proxyAddress = sTestDetails.get().get("proxyAddress").trim();
	String proxyPort = sTestDetails.get().get("proxyPort").trim();
	String browser = driverType.trim().toUpperCase().replaceAll("[^A-Z]", "");
    Predicate<Entry> byUrl = entry -> entry.getRequest().getUrl().trim().endsWith(".ts");
    List<Entry> filteredLogs = utils.getFilteredNetworkLogs(byUrl, browser, udid, proxyPort, proxyAddress);
    ```
- Traverse through the filtered logs and put an assert on it.
- Since network logs can be huge in size depending on the scenario, we flush old logs if size of logs reaches 4 to 5MB.
- To get all network logs, call below api
    ```
    curl -X GET \
      http://<proxyAddress>/api/v1/proxy/{{browser}}/{{udid}}/{{port}}/all \
      -H 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json'
    ```
- It can happen while traversing, we are reading same logs again and again after regular time interval. To solve this problem, we have added `startedDateTime` field in the logs. This can be used while traversal and avoid logs if you encountered same timestamp in this field.
    ```
    String updatedStartDateTime = "";
	if(lastStartDateTime.trim().equalsIgnoreCase("")) {
		for(int i = 0; i < filteredLogs.size(); i++) {
			Entry entry = filteredLogs.get(i);
			Request request = entry.getRequest();
			/** Your code here **/
		}
	} else {
		for(int i = filteredLogs.size() - 1; i >= 0; i--) {
			Entry entry = filteredLogs.get(i);
			if(!lastStartDateTime.trim().equalsIgnoreCase("") && entry.getStartedDateTime().trim().equalsIgnoreCase(lastStartDateTime)) {
				break;
			}
			Request request = entry.getRequest();
			/** Your code here **/
		}
	}
	updatedStartDateTime = filteredLogs.get(filteredLogs.size() - 1).getStartedDateTime().trim();
    ```

### Assert on ADB logs
- Call `in.wynk.common.Utils` `getFilteredAdbLogs(String udid, String filter)` method.
- `filter` parameter accepts any String value with which you want to search for in ADB logs.
- Traverse through the filtered logs and put an assert on it.