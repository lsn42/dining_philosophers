package main;

public class Main {

    public int mode;
    public int speed;
    public Philosopher[] philosophers;
    public Chopstick[] chopsticks;
    public GUI gui;

    public Main(int mode) {
        this.mode = mode;
        this.speed = 128;
        this.philosophers = new Philosopher[5];
        this.chopsticks = new Chopstick[5];
        this.gui = new GUI();
        this.gui.addListener(new MainListener());
        this.selectMode(this.mode);
    }

    public Main() {
        this(0);
    }

    public void selectMode(int mode) {
        for (int i = 0; i < 5; ++i) {
            if (this.philosophers[i] != null) {
                this.philosophers[i].end();
            }
        }
        if (mode == 0) {
            // normal mode
            for (int i = 0; i < 5; ++i) {
                this.philosophers[i] = new Philosopher(i);
                this.philosophers[i].addListener(this.gui);
                this.chopsticks[i] = new Chopstick();
            }
            for (int i = 0; i < 5; ++i) {
                this.philosophers[i].rightChopstick = this.chopsticks[i];
                this.philosophers[i].leftChopstick = this.chopsticks[(i+1)%5];
            }
        } else if (mode == 1) {
            // one of the philosophers pick left chopstick first
            this.philosophers[0] = new LeftHandedPhilosopher(0);
            this.philosophers[0].addListener(this.gui);
            this.chopsticks[0] = new Chopstick();
            for (int i = 1; i < 5; ++i) {
                this.philosophers[i] = new Philosopher(i);
                this.philosophers[i].addListener(this.gui);
                this.chopsticks[i] = new Chopstick();
            }
            for (int i = 0; i < 5; ++i) {
                this.philosophers[i].rightChopstick = this.chopsticks[i];
                this.philosophers[i].leftChopstick = this.chopsticks[(i+1)%5];
            }
        }
        this.setSpeed(this.speed);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (0 < speed & speed < 256) {
            for (int i = 0; i < 5; ++i) {
                this.philosophers[i].interval = (256-speed)*20;
            }
        } else {
            for (int i = 0; i < 5; ++i) {
                this.philosophers[i].interval = 0;
            }
        }
    }

    public void run() {
        for (Philosopher i: this.philosophers) {
            i.start();
        }
    }

    public class MainListener implements Listener {
        @Override
        public void actionPerformed(Event e) {
            if (e.getSource() instanceof GUI & (GUI)e.getSource() == Main.this.gui) {
                if (e.command[0].equals("mode")) {
                    int mode = Integer.parseInt(e.command[1]);
                    if (mode != Main.this.mode) {
                        Main.this.mode = mode;
                        Main.this.gui.reset();
                        Main.this.selectMode(mode);
                        Main.this.run();
                    }
                } else if (e.command[0].equals("speed")) {
                    int speed = Integer.parseInt(e.command[1]);
                    Main.this.setSpeed(speed);
                } else if (e.command[0].equals("restart")) {
                    Main.this.gui.reset();
                    Main.this.selectMode(Main.this.mode);
                    Main.this.run();
                }
            }
        }
    }

    public static void main(String args[]) {
        Main m = new Main();
        m.run();
        while (true) {
            int eaten = 0;
            for (int i = 0; i < 5; ++i) {
                eaten += m.philosophers[i].eaten;
            }
            System.out.println();
            m.gui.setEaten(eaten);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}