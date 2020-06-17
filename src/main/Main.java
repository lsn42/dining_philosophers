package main;

public class Main {
    public static void main(String args[]) {
        Philosopher[] p = new Philosopher[5];
        Chopstick[] c = new Chopstick[5];
        GUI g = new GUI();
        for (int i = 0; i < 5; ++i) {
            p[i] = new Philosopher(i);
            p[i].addListener(g);
            c[i] = new Chopstick();
        }
        for (int i = 0; i < 5; ++i) {
            p[i].chopstick1 = c[i];
            p[i].chopstick2 = c[(i+1)%5];
        }
        for (Philosopher i: p) {
            i.start();
        }
        while (true) {
            for (int i = 0; i < 5; ++i) {
                System.out.print("Philosopher"+i+" is "+p[i].status);
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