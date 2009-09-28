package net.bodz.mda.mim;

public interface TextPosition {

    Object getSource();

    long getOffset();

    int getLength();

    int getLine();

    int getColumn();

}
