package net.bodz.xml.xmod.modpdb;

import net.sf.freejava.util.Pair;

public class Cell extends Pair<Integer, Object> {

    private static final long serialVersionUID = -3500506439460276952L;

    public Cell() {
        super();
    }

    public Cell(Integer first, Object second) {
        super(first, second);
    }

    public Cell(Integer first) {
        super(first);
    }

    public Integer getIndex() {
        return first;
    }

    public void setIndex(Integer index) {
        this.first = index;
    }

}
