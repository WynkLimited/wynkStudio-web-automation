#!/usr/bin/env bash
aws s3 cp ./config.json s3://airtel-tv-catchup-dev/allconfigs/xstream-config/index.json --profile dev --region ap-south-1
echo "Config Updated"
