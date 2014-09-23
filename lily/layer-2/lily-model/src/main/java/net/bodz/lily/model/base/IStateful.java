package net.bodz.lily.model.base;

public interface IStateful {

    int S_INIT = 0;
    int S_ERROR = -1;
    int S_ACCEPT = 1;
    int S_LOCKED = 2;

    int getState();

    void setState(int state);

}
