import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public Test() {
        System.out.print("默认构造方法！--");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！--");
    }

    //静态代码块
    static {
        System.out.print("静态代码块！--");
    }

    private static void test() {
        System.out.print("静态方法中的内容! --");
        {
            System.out.print("静态方法中的代码块！--");
        }

    }

    public static void main(String[] args) {
//        Test test = new Test();
//        Test.test();//静态代码块！--静态方法中的内容! --静态方法中的代码块！--
//        Test newTest = new Test();
//        Stack<String> a = new Stack<>();
//        LinkedList<String> b = new LinkedList<>();
//        Queue
//        a.push("123");
//        Stack<String> b = new Stack<>();
//        b = a; // 赋值的是地址！！！
//        a = new Stack<>();
    }
}