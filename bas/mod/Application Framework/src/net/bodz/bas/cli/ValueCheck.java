package net.bodz.bas.cli;

public interface ValueCheck {

    void check(Object val) throws ValueCheckFailure;

}
