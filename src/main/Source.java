package main;

public interface Source {
    
    public void addListener(Listener l);
    public void removeListener(Listener l);
    public void notifyAll(Event e);
}