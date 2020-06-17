package main;

public class LeftHandedPhilosopher extends Philosopher {

    public LeftHandedPhilosopher(int index) {
        super(index);
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                this.think();
                this.paintStatus("waiting");
                this.paintDialog("both");
                this.leftChopstick.acquire();
                this.paintChopstick("pick", "left");
                this.paintDialog("right");
                this.rightChopstick.acquire();
                this.paintChopstick("pick", "right");
                this.paintDialog("end");
                this.eat();
                this.leftChopstick.release();
                this.paintChopstick("release", "left");
                this.rightChopstick.release();
                this.paintChopstick("release", "right");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}