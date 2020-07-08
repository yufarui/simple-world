#!/bin/bash
:<<EOF
变量类型
运行shell时，会同时存在三种变量：

1) 局部变量 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
2) 环境变量 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
3) shell变量 shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行
EOF

# 变量命名名规范
your_name="runoob.com"

# 使用变量-1
echo $your_name
# 推荐使用此种风格
echo ${your_name}

# 变量 使用示例-2
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill} Script"
done

# 只读变量
myUrl="https://www.google.com"
readonly myUrl
# 只读变量无法修改
# myUrl="https://www.google.com"

# 删除变量
# unset variable_name

# shell 字符串
your_name="runoob"
expression="Hello, I know you are ${your_name}! \n"
# -e 解析 转义字符
# 可以使用 man echo
echo -e "${expression}"
# 单引号无法解析 转义 和 变量

# 字符串拼接
echo "${your_name}"",""${expression}"

# 字符串长度
echo ${#expression}

# 提取字符串
echo "${expression:1:4}"
echo "${expression:0:${#expression}}"
echo "${expression:0:(${#expression}-5)}"

# 查找子字符
expr index "${expression}" o

# shell 数组
array_name=(value0 value1 value2 value3)

valuen=${array_name[n]}

echo "${valuen}"
echo "${array_name[0]}"
echo "${array_name[@]}"
echo "${array_name[*]}"
echo ${#array_name[*]}

date
