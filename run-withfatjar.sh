#!/bin/sh

#set -x

export srchField=$1
export srchValue=$2
export file=$3

BASEDIR=$(dirname "$0")
PARQUETQ_HOME=$BASEDIR

PARQUETQ_MAINJAR=Parquet-vis-select-0.0.1-SNAPSHOT.jar

export tmpFile=`echo $3 | awk -F "/" '{print $NF}'`

parquet-tools cat --json $3 > /tmp/tmp_${tmpFile}.txt

export totalFileCount=`cat /tmp/tmp_${tmpFile}.txt | wc -l | awk '{print $1}'`

echo "Total File Count is : $totalFileCount"

java -jar ${BASEDIR}/${PARQUETQ_MAINJAR} $1 $2 /tmp/tmp_${tmpFile}.txt