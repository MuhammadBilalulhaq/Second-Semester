public class MultiThreadExample01 {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread t = new MyThread("Task " + i);
            t.start();
        }
    }
}

class MyThread extends Thread {
    private String name;
    public static int count = 0;

    MyThread(String name) {
        this.name = name;
    }

    public void run() {
        count++;
        System.out.println(name + " is running in " 
                           + Thread.currentThread().getName() 
                           + " | count = " + count);
    }
}
