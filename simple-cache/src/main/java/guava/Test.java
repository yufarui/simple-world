package guava;

import source.Getter;

@Getter
public class Test {
    private String value;

    public Test(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Test test = new Test("123");
//        System.out.println(test.getValue());
    }
}
