#!/bin/bash
rm -rf webapps/tracksafe 
rm -rf webapps/tracksafe.war
cp /Users/pritesh/App/git/tracksafeserver/target/tracksafe.war webapps/
cp /dev/null logs/trackserver.log
#/usr/local/apache-tomcat-7.0.42/bin/shutdown.sh
ps -ef | grep tomcat | awk '{print $2}' | xargs kill -9
/usr/local/apache-tomcat-7.0.42/bin/startup.sh
