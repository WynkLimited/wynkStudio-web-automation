#!/usr/bin/env bash

DEVICE=$1
DEVICE_ID=$2
PORT=$3
FILTER=$4
mitmdump -s ./dump_har.py --set hardump=./${DEVICE}_${DEVICE_ID}_$PORT.json --set jsondump=./${DEVICE}_${DEVICE_ID}_${PORT}_FLUSHED.json --set request_host=$FILTER -p $PORT --set block_global=false

