package yu;

/**
 * @date: 2020/7/7 14:47
 * @author: farui.yu
 */
public class ShellLogTest {
    /**
     * 测试 2>&1
     * 标准错误输出 重定向到 标准输出
     * @link https://www.jb51.net/article/169778.htm
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("标准输出");
        System.err.println("标准错误输出");
        throw new RuntimeException("异常");
    }
}
