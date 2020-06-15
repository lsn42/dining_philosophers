package main;

public class Main {
    public static void main(String args[]) {
        Philosopher[] p = new Philosopher[5];
        Chopstick[] c = new Chopstick[5];
        for (int i = 0; i < 5; ++i) {
            p[i] = new Philosopher();
            c[i] = new Chopstick();
        }
        for (int i = 0; i < 5; ++i) {
            p[i].chopstick1 = c[i];
            p[i].chopstick2 = c[(i+4)%5];
        }
        for (Philosopher i: p) {
            i.start();
        }
        while (true) {
            for (int i = 0; i < 5; ++i) {
                System.out.print("Philosopher"+i+" is "+p[i].state);
                System.out.print(", he has "+p[i].chopstick+" chopstick(s) now");
                System.out.println(", he had ate "+p[i].eaten+" time(s).");
            }
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}