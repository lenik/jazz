package net.bodz.mda.parsers;

public interface Token {

    int getId();

    String getName();

    Object value();

    int getPreStart();

    int getPreStartLine();

    int getPreStartColumn();

    int getStart();

    int getStartLine();

    int getStartColumn();

    int getEnd();

    int getEndLine();

    int getEndColumn();

    String getText();

    int getLength();

    char getChar(int index);

}
