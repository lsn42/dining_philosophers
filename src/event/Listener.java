package event;

import java.util.EventListener;

public interface Listener extends EventListener {
    
    public void actionPerformed(Event e);
}