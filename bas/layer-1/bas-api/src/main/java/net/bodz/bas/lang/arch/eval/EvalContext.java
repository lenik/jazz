package net.bodz.bas.lang.arch.eval;

import java.util.ArrayList;
import java.util.List;

public class EvalContext {

    private List<Object> stack;
    private int end;
    private List<Integer> frames;
    private int frameend;

    public EvalContext() {
        stack = new ArrayList<Object>();
        end = -1;
        frames = new ArrayList<Integer>();
        frameend = -1;
    }

    public void push(Object o) {
        stack.add(o);
        end++;
    }

    public void push(Object... ol) {
        for (Object o : ol)
            stack.add(o);
        end += ol.length;
    }

    public Object pop() {
        return stack.remove(end--);
    }

    public void pop(Object[] objs, int off, int len) {
        while (len-- > 0)
            objs[off++] = stack.remove(end--);
    }

    public void pop(Object[] objs) {
        pop(objs, 0, objs.length);
    }

    public void enter() {
        frames.add(end);
        frameend++;
    }

    public void enter(Object arg) {
        enter();
        push(arg);
    }

    public void enter(Object... args) {
        enter();
        push(args);
    }

    public void leave() {
        int prevend = frames.remove(frameend--);
        assert prevend <= end : "stack corrupt"; 
        while (end > prevend)
            stack.remove(end--);
    }

    protected void check(int index) {
        assert frameend >= 0 : "no frame"; 
        int fsize = end - frames.get(frameend);
        if (index >= fsize)
            throw new IndexOutOfBoundsException("frame size: " + fsize); 
    }

    public Object get(int index) {
        assert end == stack.size() - 1;
        check(index);
        return stack.get(end - index);
    }

    public Object[] get() {
        assert frameend >= 0 : "no frame"; 
        int fsize = end - frames.get(frameend);
        Object[] objs = new Object[fsize];
        for (int i = 0; i < fsize; i++)
            objs[i] = stack.get(end - i);
        return objs;
    }

    public void getfast(Object[] objs, int off, int len) {
        check(len - 1);
        pop(objs, off, len);
    }

    private static ThreadLocal<EvalContext> localContext;
    static {
        localContext = new ThreadLocal<EvalContext>();
    }

    public static EvalContext getInstance() {
        EvalContext context = localContext.get();
        if (context == null)
            localContext.set(context = new EvalContext());
        return context;
    }

}
