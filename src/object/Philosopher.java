package object;

import java.util.HashSet;

import event.Event;
import event.Listener;
import event.Source;

public class Philosopher extends Thread implements Source {

    public String status;
    public boolean isEnd;
    public int index;
    public int eaten;
    public int interval;
    public Chopstick rightChopstick;
    public Chopstick leftChopstick;

    public HashSet<Listener> listeners;

    public Philosopher(int index) {
        this.status = "thinking";
        this.isEnd = false;
        this.index = index;
        this.eaten = 0;
        this.interval = 2560;
        this.listeners = new HashSet<Listener>();
    }

    public void eat() {
        this.paintStatus("eating");
        try {
            Thread.sleep((int)(Math.random()*this.interval));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++this.eaten;
    }

    public void think() {
        this.paintStatus("thinking");
        try {
            Thread.sleep((int)(Math.random()*this.interval*2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!isEnd) {
                this.think();
                this.paintStatus("waiting");
                this.paintDialog("both");
                this.rightChopstick.acquire();
                this.paintChopstick("pick", "right");
                this.paintDialog("left");
                this.leftChopstick.acquire();
                this.paintChopstick("pick", "left");
                this.paintDialog("end");
                this.eat();
                this.rightChopstick.release();
                this.paintChopstick("release", "right");
                this.leftChopstick.release();
                this.paintChopstick("release", "left");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        this.isEnd = true;
        for (Listener l: this.listeners) {
            this.removeListener(l);
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