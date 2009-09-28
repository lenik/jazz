package net.bodz.mda.mim;

public interface Block {

    BlockType getBlockType();

    Boundary getBoundary();

    TextPosition getPosition();

    String getText();

}
