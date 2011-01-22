package net.bodz.geom.drawtarget;

public interface DelegateDrawTarget2f extends DrawTarget2f {

    DrawTarget2f getDelegant();

    void setDelegant(DrawTarget2f delegant);
}
