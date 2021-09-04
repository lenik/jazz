package net.bodz.bas.t.set;

public interface IMarkset {

    boolean containsMark(Object o);

    boolean addMark(Object o);

    boolean removeMark(Object o);

    void clearMarks();

}
