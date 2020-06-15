package main;

import java.util.concurrent.Semaphore;

public class Chopstick extends Semaphore {

    private static final long serialVersionUID = 1L;

    public Chopstick() {
        super(1);
    }
}