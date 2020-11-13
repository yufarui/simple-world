#!/bin/bash
:<<EOF
变量的传递
EOF

:<<EOF
使用命令行 传入参数
./variable_pass.sh 1 2 3
EOF

echo "Shell 传递参数实例！";
echo "执行的文件名：${0}";
echo "第一个参数为：${1}";
echo "第二个参数为：${2}";
echo "第三个参数为：${3}";

:<<EOF
$#	传递到脚本的参数个数
$*	以一个单字符串显示所有向脚本传递的参数。
如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数。
$$	脚本运行的当前进程ID号
$!	后台运行的最后一个进程的ID号
$@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数。
$-	显示Shell使用的当前选项，与set命令功能相同。
$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。
EOF

# 建议 使用$@
for i in "$@" ; do
    echo "${i}"
done

echo $#

# shell 0 表示true
echo $?


doUpdateRepo(){
  echo "test"
}

doUpdateRepo

for i in pwd ls java -version; do
    ${i}
done
