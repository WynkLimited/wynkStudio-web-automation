#!/bin/bash
counter=0
IFS=';' read -ra ADDR <<< "$TAGS"
for i in "${ADDR[@]}"; do
    # process "$i"
    if [[ "$counter" -eq 0 ]]; then
    	C_JOB_NAME=${JOB_NAME}
    else
    	C_JOB_NAME=${JOB_NAME}-${counter}
    fi
    if [[ "$PERFORMANCE" == "false" ]]; then
    	C_TAGS="-t $i -t ~@performance"
    else
    	C_TAGS="-t $i"	
    fi
    if [[ "$RUN_FAILED_SCENARIOS" == "true" ]]; then
	    C_TAGS="@user-files/testng/rerun.txt"
	fi
	C_DEVICES_CONFIGURATION=$(echo $DEVICES_CONFIGURATION | cut -d "|" -f $((counter+1)))
	{
		echo mvn generate-test-resources -Ptest -Denv=$ENV -Dbrowser=$BROWSER -Dcountry_code=$COUNTRY_CODE -DwriteToFile=true -DmaxThreads=$MAX_THREADS -Ddevicefarm=$DEVICEFARM -DappVersion=$APP_VERSION -DrunFailedScenarios=$RUN_FAILED_SCENARIOS -DdevicefarmUser=$DEVICEFARM_USER -DdeviceId=$DEVICE_ID -DincludeBusyDevices=$INCLUDE_BUSY_DEVICES -DdevicefarmHub=$DEVICEFARM_HUB -DdevicefarmHost=$DEVICEFARM_HOST -Dnetworkaddress.cache.ttl=10 -Dsun.net.inetaddr.ttl=10 -DincludeOtherUserBusyDevices=$INCLUDE_OTHER_USER_BUSY_DEVICES -DslackHandle="$SLACK_HANDLE" -DslackWebHook=$SLACK_WEB_HOOK -DslackNotification=$SLACK_NOTIFICATION -DJOB_NAME=$C_JOB_NAME -Dpod=$POD -DdevicesConfiguration="$C_DEVICES_CONFIGURATION" -Dmaven.test.failure.ignore=false -DdevicefarmReserveDevice=$DEVICEFARM_RESERVE_DEVICE
  	cp -r conf/$${PROJECT_NAME}/ src
		mvn generate-test-resources -Ptest -Denv=$ENV -Dbrowser=$BROWSER -Dcountry_code=$COUNTRY_CODE -DwriteToFile=true -DmaxThreads=$MAX_THREADS -Ddevicefarm=$DEVICEFARM -DappVersion=$APP_VERSION -DrunFailedScenarios=$RUN_FAILED_SCENARIOS -DdevicefarmUser=$DEVICEFARM_USER -DdeviceId=$DEVICE_ID -DincludeBusyDevices=$INCLUDE_BUSY_DEVICES -DdevicefarmHub=$DEVICEFARM_HUB -DdevicefarmHost=$DEVICEFARM_HOST -Dnetworkaddress.cache.ttl=10 -Dsun.net.inetaddr.ttl=10 -DincludeOtherUserBusyDevices=$INCLUDE_OTHER_USER_BUSY_DEVICES -DslackHandle="$SLACK_HANDLE" -DslackWebHook=$SLACK_WEB_HOOK -DslackNotification=$SLACK_NOTIFICATION -DJOB_NAME=$C_JOB_NAME -Dpod=$POD -DdevicesConfiguration="$C_DEVICES_CONFIGURATION" -Dmaven.test.failure.ignore=false -DdevicefarmReserveDevice=$DEVICEFARM_RESERVE_DEVICE
	} && {
		JSON="json:target/cucumber-report/cucumber-report-feature-composite.json"
		RERUN_FILENAME="rerun:target/rerun.txt"
		echo mvn verify -Pfailsafe -Denv=$ENV -Dtestng-suite-xml="testng-customsuite.xml" -Dcucumber.options="user-files/features" -Dcucumber.options="$C_TAGS -t ~@wip -g $C_GLUE -p $JSON -p pretty -p $RERUN_FILENAME" -DappName=$APP_NAME -Duninstall=$UNINSTALL -Ddevicefarm=$DEVICEFARM -DappVersion=$APP_VERSION -DBUILD_USER_ID=$BUILD_USER_ID -DvideoGifIntegration=$VIDEO_GIF_INTEGRATION -DenvCheck=$ENV_CHECK -DgetADBLogs=$GET_ADB_LOGS -DslackNotification=$SLACK_NOTIFICATION -DgetPageSourceOnReporting=$GET_PAGESOURCE_ON_REPORTING -DrunOnProxy=$RUN_ON_PROXY -DjenkinsIp=$JENKINS_IP -DJOB_NAME=$C_JOB_NAME -DdeviceId=$DEVICE_ID -DincludeBusyDevices=$INCLUDE_BUSY_DEVICES -DnoReset=$NO_RESET -DtakeScreenshot=$TAKE_SCREENSHOT -Dfilter=$FILTER -DsendDataToES=$SEND_DATA_TO_ES -DperformanceData=$PERFORMANCE_DATA -DattachNetworkLogs=$ATTACH_NETWORK_LOGS -DproxyAddress=$PROXY_ADDRESS -DwifiName="$WIFI_NAME" -DmaxThreads=$MAX_THREADS -DgenerateConsolidatedReport=true -DappPackage=$APP_PACKAGE -DdevicefarmHub=$DEVICEFARM_HUB -DdevicefarmHost=$DEVICEFARM_HOST -DbundleId=$BUNDLE_ID -DiosAppExtension=$IOS_APP_EXTENSION -DuploadReportToS3=false -DslackHandle="$SLACK_HANDLE" -Dnetworkaddress.cache.ttl=10 -Dsun.net.inetaddr.ttl=10 -DincludeOtherUserBusyDevices=$INCLUDE_OTHER_USER_BUSY_DEVICES -DslackWebHook=$SLACK_WEB_HOOK -DchromedriverPath=$CHROMEDRIVER_PATH -Dpod=$POD -DgitBranch=$GIT_BRANCH -DdevicesConfiguration="$C_DEVICES_CONFIGURATION" -DemailNotification=$EMAIL_NOTIFICATION -Dto="$TO" -Dcc="$CC" -Dbcc="$BCC" -DgenerateSSUsingADB=$GENERATE_SS_USING_ADB -Dmaven.test.failure.ignore=false -Dtags=$i -DsendPerformanceDataToES=$SEND_PERFORMANCE_DATA_TO_ES -Dperformance=$PERFORMANCE -DtestCaseSheet=$TEST_CASE_SHEET -DkibanaUrl=$KIBANA_URL -DattachDevicefarmAppiumLogs=$ATTACH_APPIUM_LOGS -DgenerateGifOnlyOnFailure=$GENERATE_GIF_ONLY_ON_FAILURE -DattachADBLogsOnFail=$ATTACH_ADB_LOGS_ON_FAIL -DdeveloperEmailAddress=$DEVELOPER_EMAIL_ADDRESS -DfirebaseDataESUpload=$FIREBASE_DATA_ES_UPLOAD -DBUILD_NUMBER=$BUILD_NUMBER -DforceAppDownloadFromS3=$FORCE_APP_DOWNLOAD_FROM_S3 -DgetCurrentAppVersionFromApi=$GET_CURRENT_APP_VERSION_FROM_API -Drun_analytics=$RUN_ANALYTICS -DrestartAdbOnDemand=$RESTART_ADB_ON_DEMAND -DremoveSongs=$REMOVE_SONGS -DdevicefarmReserveDevice=$DEVICEFARM_RESERVE_DEVICE -DuninstallThirdPartyApps=$UNINSTALL_THIRD_PARTY_APPS -Dgrid=$GRID -DFEEDBACK_ID=$FEEDBACK_ID -DCOMMIT_ID=$COMMIT_ID -DCOMMENT=$COMMENT -DDEVELOPER_NAME=$DEVELOPER_NAME -DSOURCE_BRANCH=$SOURCE_BRANCH -DTARGET_BRANCH=$TARGET_BRANCH -DPR_NUMBER=$PR_NUMBER -DpassPercentage=$PASS_PERCENTAGE
		mvn verify -Pfailsafe -Denv=$ENV -Dtestng-suite-xml="testng-customsuite.xml" -Dcucumber.options="user-files/features" -Dcucumber.options="$C_TAGS -t ~@wip -g $C_GLUE -p $JSON -p pretty -p $RERUN_FILENAME" -DappName=$APP_NAME -Duninstall=$UNINSTALL -Ddevicefarm=$DEVICEFARM -DappVersion=$APP_VERSION -DBUILD_USER_ID=$BUILD_USER_ID -DvideoGifIntegration=$VIDEO_GIF_INTEGRATION -DenvCheck=$ENV_CHECK -DgetADBLogs=$GET_ADB_LOGS -DslackNotification=$SLACK_NOTIFICATION -DgetPageSourceOnReporting=$GET_PAGESOURCE_ON_REPORTING -DrunOnProxy=$RUN_ON_PROXY -DjenkinsIp=$JENKINS_IP -DJOB_NAME=$C_JOB_NAME -DdeviceId=$DEVICE_ID -DincludeBusyDevices=$INCLUDE_BUSY_DEVICES -DnoReset=$NO_RESET -DtakeScreenshot=$TAKE_SCREENSHOT -Dfilter=$FILTER -DsendDataToES=$SEND_DATA_TO_ES -DperformanceData=$PERFORMANCE_DATA -DattachNetworkLogs=$ATTACH_NETWORK_LOGS -DproxyAddress=$PROXY_ADDRESS -DwifiName="$WIFI_NAME" -DmaxThreads=$MAX_THREADS -DgenerateConsolidatedReport=true -DappPackage=$APP_PACKAGE -DdevicefarmHub=$DEVICEFARM_HUB -DdevicefarmHost=$DEVICEFARM_HOST -DbundleId=$BUNDLE_ID -DiosAppExtension=$IOS_APP_EXTENSION -DuploadReportToS3=false -DslackHandle="$SLACK_HANDLE" -Dnetworkaddress.cache.ttl=10 -Dsun.net.inetaddr.ttl=10 -DincludeOtherUserBusyDevices=$INCLUDE_OTHER_USER_BUSY_DEVICES -DslackWebHook=$SLACK_WEB_HOOK -DchromedriverPath=$CHROMEDRIVER_PATH -Dpod=$POD -DgitBranch=$GIT_BRANCH -DdevicesConfiguration="$C_DEVICES_CONFIGURATION" -DemailNotification=$EMAIL_NOTIFICATION -Dto="$TO" -Dcc="$CC" -Dbcc="$BCC" -DgenerateSSUsingADB=$GENERATE_SS_USING_ADB -Dmaven.test.failure.ignore=false -Dtags=$i -DsendPerformanceDataToES=$SEND_PERFORMANCE_DATA_TO_ES -Dperformance=$PERFORMANCE -DtestCaseSheet=$TEST_CASE_SHEET -DkibanaUrl=$KIBANA_URL -DattachDevicefarmAppiumLogs=$ATTACH_APPIUM_LOGS -DgenerateGifOnlyOnFailure=$GENERATE_GIF_ONLY_ON_FAILURE -DattachADBLogsOnFail=$ATTACH_ADB_LOGS_ON_FAIL -DdeveloperEmailAddress=$DEVELOPER_EMAIL_ADDRESS -DfirebaseDataESUpload=$FIREBASE_DATA_ES_UPLOAD -DBUILD_NUMBER=$BUILD_NUMBER -DforceAppDownloadFromS3=$FORCE_APP_DOWNLOAD_FROM_S3 -DgetCurrentAppVersionFromApi=$GET_CURRENT_APP_VERSION_FROM_API -Drun_analytics=$RUN_ANALYTICS -DrestartAdbOnDemand=$RESTART_ADB_ON_DEMAND -DremoveSongs=$REMOVE_SONGS -DdevicefarmReserveDevice=$DEVICEFARM_RESERVE_DEVICE -DuninstallThirdPartyApps=$UNINSTALL_THIRD_PARTY_APPS -Dgrid=$GRID -DFEEDBACK_ID=$FEEDBACK_ID -DCOMMIT_ID=$COMMIT_ID -DCOMMENT=$COMMENT -DDEVELOPER_NAME=$DEVELOPER_NAME -DSOURCE_BRANCH=$SOURCE_BRANCH -DTARGET_BRANCH=$TARGET_BRANCH -DPR_NUMBER=$PR_NUMBER -DpassPercentage=$PASS_PERCENTAGE
		echo mvn generate-test-resources -Ppostaction -Denv=$ENV -DJOB_NAME=$C_JOB_NAME -Dmaven.test.failure.ignore=false
		mvn generate-test-resources -Ppostaction -Denv=$ENV -DJOB_NAME=$C_JOB_NAME -Dmaven.test.failure.ignore=false
	}
	counter=$((counter+1))
done