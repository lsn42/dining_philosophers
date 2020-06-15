package main;

public class Philosopher extends Thread {
    public String state;
    public int eaten;
    public int chopstick;
    public Chopstick chopstick1;
    public Chopstick chopstick2;
    public void eat() {
        this.state = "eating";
        try {
            Thread.sleep(500+(int)(Math.random()*500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++this.eaten;
    }
    public void think() {
        this.state = "thinking";
        try {
            Thread.sleep(1000+(int)(Math.random()*500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start() {
        this.state = "good";
        this.eaten = 0;
        this.chopstick = 0;
        super.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                this.think();
                this.state = "waiting";
                this.chopstick1.acquire();
                ++this.chopstick;
                this.chopstick2.acquire();
                ++this.chopstick;
                this.eat();
                this.chopstick1.release();
                --this.chopstick;
                this.chopstick2.release();
                --this.chopstick;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}