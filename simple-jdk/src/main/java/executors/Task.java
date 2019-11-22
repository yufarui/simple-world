package executors;

public class Task extends Thread {
    private Integer value;
    private String inner = "create";

    public Task(Integer value) {
        this.value = value;
        this.setName(value + "");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
    }
}
