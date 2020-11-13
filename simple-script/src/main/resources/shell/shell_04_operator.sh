#!/bin/bash
:<<EOF
算术运算符
EOF

echo "两数之和为 : $((1+2))"

a=1
b=2

echo "两数之和为 : $((a+b))"

if [ ${a} == ${b} ]
then
   echo "a 等于 b"
elif [ ${a} != ${b} ]
then
   echo "a 不等于 b"
fi

:<<EOF
-eq	检测两个数是否相等，相等返回 true。	[ ${a} -eq ${b} ] 返回 false。
-ne	检测两个数是否不相等，不相等返回 true。	[ ${a} -ne ${b} ] 返回 true。
-gt	检测左边的数是否大于右边的，如果是，则返回 true。	[ ${a} -gt ${b} ] 返回 false。
-lt	检测左边的数是否小于右边的，如果是，则返回 true。	[ ${a} -lt ${b} ] 返回 true。
-ge	检测左边的数是否大于等于右边的，如果是，则返回 true。	[ ${a} -ge ${b} ] 返回 false。
-le	检测左边的数是否小于等于右边的，如果是，则返回 true。	[ ${a} -le ${b} ] 返回 true。
EOF

if [ ${a} -eq ${b} ]
then
   echo " ${a} -eq ${b} : a 等于 b"
else
   echo " ${a} -eq ${b} : a 不等于 b"
fi

:<<EOF
位运算
!	非运算，表达式为 true 则返回 false，否则返回 true。	[ ! false ] 返回 true。
-o	或运算，有一个表达式为 true 则返回 true。	[ $a -lt 20 -o $b -gt 100 ] 返回 true。
-a	与运算，两个表达式都为 true 才返回 true。	[ $a -lt 20 -a $b -gt 100 ] 返回 false。
EOF

:<<EOF
逻辑运算
&&	逻辑的 AND	[[ $a -lt 100 && $b -gt 100 ]] 返回 false
||	逻辑的 OR	[[ $a -lt 100 || $b -gt 100 ]] 返回 true
EOF

:<<EOF
=	检测两个字符串是否相等，相等返回 true。	[ $a = $b ] 返回 false。
!=	检测两个字符串是否相等，不相等返回 true。	[ $a != $b ] 返回 true。
-z	检测字符串长度是否为0，为0返回 true。	[ -z $a ] 返回 false。
-n	检测字符串长度是否不为 0，不为 0 返回 true。	[ -n "$a" ] 返回 true。
$	检测字符串是否为空，不为空返回 true。	[ $a ] 返回 true。
EOF

:<<EOF
-b file	检测文件是否是块设备文件，如果是，则返回 true。	[ -b $file ] 返回 false。
-c file	检测文件是否是字符设备文件，如果是，则返回 true。	[ -c $file ] 返回 false。
-d file	检测文件是否是目录，如果是，则返回 true。	[ -d $file ] 返回 false。
-f file	检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。	[ -f $file ] 返回 true。
-g file	检测文件是否设置了 SGID 位，如果是，则返回 true。	[ -g $file ] 返回 false。
-k file	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。	[ -k $file ] 返回 false。
-p file	检测文件是否是有名管道，如果是，则返回 true。	[ -p $file ] 返回 false。
-u file	检测文件是否设置了 SUID 位，如果是，则返回 true。	[ -u $file ] 返回 false。
-r file	检测文件是否可读，如果是，则返回 true。	[ -r $file ] 返回 true。
-w file	检测文件是否可写，如果是，则返回 true。	[ -w $file ] 返回 true。
-x file	检测文件是否可执行，如果是，则返回 true。	[ -x $file ] 返回 true。
-s file	检测文件是否为空（文件大小是否大于0），不为空返回 true。	[ -s $file ] 返回 true。
-e file	检测文件（包括目录）是否存在，如果是，则返回 true。	[ -e $file ] 返回 true。
EOF

# [] 操作 可以替换成 test
if test ! ${a} -eq ${b}
then
  echo "不相等"
else
  echo "相等"
fi