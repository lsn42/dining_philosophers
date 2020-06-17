package main;

import java.util.EventObject;

public class Event extends EventObject {

    private static final long serialVersionUID = 1L;

    public String[] command;
    
    public Event(Source source, String... command) {
        super(source);
        this.command = command;
    }
}