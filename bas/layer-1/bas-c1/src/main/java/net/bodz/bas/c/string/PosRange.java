package net.bodz.bas.c.string;

public class PosRange {

    public int begin;
    public int end;

    public PosRange() {
    }

    public PosRange(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int size() {
        return end - begin;
    }

    public String run(String s) {
        return s.substring(begin, end);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d)", begin, end);
    }

}
