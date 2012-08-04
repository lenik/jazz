package net.bodz.swt.gui;

import java.util.EventListener;
import java.util.EventObject;

public abstract class EventHandler
        implements EventListener {

    public abstract void handle(EventObject e);

}
