package yu;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

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
        System.out.println(Base64.decode("eyJleHAiOjI0NTU4NTQ2MTksImtzRG9tYWluVXNlciI6eyJwYXNzd29yZCI6bnVsbCwidXNlcm5hbWUiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbXSwiYWNjb3VudE5vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZSwiZW5hYmxlZCI6dHJ1ZSwidXNlcklkIjoiYWRtaW4iLCJkaXNwbGF5TmFtZSI6IueuoeeQhuWRmCJ9LCJ1c2VyX25hbWUiOiJhZG1pbiIsImp0aSI6IjFiMWI5OTMzLTFiZjAtNDJhYS05ZmM3LTU1NDQwNjIzOTIzYyIsImNsaWVudF9pZCI6InRlc3Rqd3RjbGllbnRpZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ"));
    }
}