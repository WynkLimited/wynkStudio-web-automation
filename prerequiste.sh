#!/bin/bash
export C_TAGS="-t $TAGS"
if [[ "$RUN_FAILED_SCENARIOS" == "true" ]]; then
    export C_TAGS="@user-files/testng/rerun.txt"
fi
export GLUE="in.wynk.steps"
export GET_ADB_LOGS="true"
export JENKINS_IP="http://10.90.31.13:8081"
export C_GLUE=${GLUE//,/" -g "}
export GET_PAGESOURCE_ON_REPORTING="false"
