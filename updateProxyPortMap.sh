#!/usr/bin/env bash
aws s3 cp $PWD/src/DevicefarmProxyPortMap.json s3://airtel-tv-catchup-dev/allconfigs/DevicefarmProxyPortMap/index.json --region ap-south-1
