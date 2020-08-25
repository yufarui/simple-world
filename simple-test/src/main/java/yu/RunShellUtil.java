package yu;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;
import java.text.MessageFormat;

public class RunShellUtil {

    public static String runScript(String[] cmd) {
        StringBuffer buf = new StringBuffer();
        String rt;
        String ln;
        try {
            Process pos = Runtime.getRuntime().exec(cmd);
            pos.waitFor();
            try(LineNumberReader input = new LineNumberReader(new InputStreamReader(pos.getInputStream()))) {
                while ((ln = input.readLine()) != null) {
                    buf.append(ln + "\n");
                }
                rt = buf.toString();
            }
        }  catch (Exception e) {
           throw new RuntimeException("execute command fail", e);
        }
        return rt;
    }

    public static void main(String[] args) {
        Connection conn = new Connection("10.243.141.138", 22);
        Session ssh = null;
        try {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword("root", "Kayak2018!");
            if (!isconn) {
                System.out.println("用户名称或者是密码不正确");
                return;
            }

            System.out.println("已经连接到" + "10.243.141.138");

            ssh = conn.openSession();

            String cmd = "for i in \"{0}\" \"{1}\" \"{2}\"; do\n" +
                    "    ${i}\n" +
                    "done";

            String format = MessageFormat.format(cmd, args);
            ssh.execCommand(format);

            InputStream is = new StreamGobbler(ssh.getStdout());
            BufferedReader brs = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String line = brs.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //连接的Session和Connection对象都需要关闭
            if (ssh != null) {
                ssh.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}