package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskTest {

    // VM options: -Xms10M -Xmx10M
    // linux :JAVA_HOME/bin/java -Xms10M -Xmx10M
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        int i = 0;
        while (true) {
            es.submit(new Task(i++));
        }
    }
}
