package net.bodz.bas.disp;

import java.util.LinkedList;

public class DispatchBean {

    LinkedList<Object> contextStack;

    public DispatchBean(Object context) {
        this.contextStack = new LinkedList<Object>();
        contextStack.removeLast();
    }

}
