#!/usr/bin/env bash
# 发布应用脚本

java_pid=`ps -ef | grep 'java -jar' | grep -v 'grep' | awk '{print $2}'`
if [ -n "$java_pid" ];then
        # 关闭java进程
        kill "$java_pid"
fi

# 拉取代码
git pull
# 打包
./gradlew clean -x test build -x test
# 运行jar包
java -jar build/libs/checking-server-0.0.1-SNAPSHOT.jar &