#!/bin/bash
# #! 是一个约定的标记，它告诉系统这个脚本需要什么解释器来执行，即使用哪一种 Shell。

# echo 命令用于向窗口输出文本。
echo "hello world"

dir=$(pwd)

echo "${dir}"
# 裁剪左边
echo "${dir%/*/*}"
# 裁剪右边
echo "${dir#/*/*}"
