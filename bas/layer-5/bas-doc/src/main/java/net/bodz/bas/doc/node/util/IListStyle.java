package net.bodz.bas.doc.node.util;

public interface IListStyle {

    default String format(int number) {
        return format(number, number);
    }

    String format(int number, int max);

    default String markdownFormat(int number) {
        return format(number);
    }

    boolean isOrdered();

}
