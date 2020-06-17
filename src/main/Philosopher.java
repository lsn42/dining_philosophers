package main;

import java.util.HashSet;

public class Philosopher extends Thread implements Source {

    public String status;
    public int eaten;
    public int index;
    public Chopstick chopstick1;
    public Chopstick chopstick2;

    public HashSet<Listener> listeners;

    public void eat() {
        this.paintStatus("eating");
        try {
            Thread.sleep(500+(int)(Math.random()*500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++this.eaten;
    }

    public void think() {
        this.paintStatus("thinking");
        try {
            Thread.sleep(1000+(int)(Math.random()*500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Philosopher(int index) {
        this.listeners = new HashSet<Listener>();
        this.status = "thinking";
        this.eaten = 0;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.think();
                this.paintStatus("waiting");
                this.paintDialog("both");
                this.chopstick1.acquire();
                this.paintChopstick("pick", "right");
                this.paintDialog("left");
                this.chopstick2.acquire();
                this.paintChopstick("pick", "left");
                this.paintDialog("end");
                this.eat();
                this.chopstick1.release();
                this.paintChopstick("release", "right");
                this.chopstick2.release();
                this.paintChopstick("release", "left");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void paintStatus(String status) {
        this.status = status;
        Event e = new Event(this, "status");
        this.notifyAll(e);
    }

    public void paintChopstick(String...situation) {
        this.notifyAll(new Event(this, situation));
    }

    public void paintDialog(String situation) {
        this.notifyAll(new Event(this, "wait", situation));
    }

    @Override
    public void addListener(Listener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeListener(Listener l) {
        this.listeners.remove(l);
    }

    @Override
    public void notifyAll(Event e) {
        for (Listener l: this.listeners) {
            l.actionPerformed(e);
        }
    }
}