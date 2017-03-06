#! /bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ROOTDIR=$DIR/..
BINDIR=$ROOTDIR/bin
APP=$BINDIR/childcare.jar

cd $ROOTDIR/static
tsc

cd $ROOTDIR
ant deploy
jar umf Manifest.txt $APP

echo must provide username and password to run
#nohup java -jar $APP $1 $2 . &
java -jar $APP $1 $2 .
