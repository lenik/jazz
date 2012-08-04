package net.bodz.swt.draw.dev;

public interface DelegateDrawTarget2f
        extends DrawTarget2f {

    DrawTarget2f getDelegant();

    void setDelegant(DrawTarget2f delegant);
}
