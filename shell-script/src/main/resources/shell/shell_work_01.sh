#!/bin/bash

doCloseService(){
  for service_name in "$@"; do
      echo "============ 开始查找,并关闭 ${service_name} 服务 ============"
      echo "$(pgrep -f "${service_name}") 服务名称"
      for pid in $(pgrep -f "${service_name}"); do
          echo "服务名称 ${service_name} 进程号 ${pid}"
          kill "${pid}"
          if test $? -eq 0
            then
              echo "成功关闭${service_name}"
            else
              echo "注意服务未正确关闭"
              exit
          fi
          done
  done
}

doUpdateRepo(){
  echo "============ 进入 $1 项目 ============"
  cd "$1" || return
  echo "============ 拉取最新代码 ============="
  git pull
  echo "============ 编译打包，跳过测试"
  mvn clean install -Dmaven.test.skip=true
  if test $? -ne 0; then
      echo "compilation failed "
      exit
  fi

}

doCloseService "ks-gateway" "ks-auth-service"

doUpdateRepo "ks-auth-repo"
cd ..

echo "============ 复制 jar包 ============"
cp ks-auth-repo/ks-gateway/target/ks-gateway-1.0.0.jar  .
cp ks-auth-repo/ks-auth-service/target/ks-auth-service-1.0.0.jar  .

echo "============ 后台启动项目 ============"
nohup java -jar ks-auth-repo/ks-gateway/target/ks-gateway-1.0.0.jar --server.port=8083 > /root/developer/ks-gateway.log 2>&1  &
nohup java -jar ks-auth-repo/ks-auth-service/target/ks-auth-service-1.0.0.jar --server.port=8082 > /root/developer/ks-auth-service.log 2>&1  &
echo "============ 成功编译项目 ============"
