package net.bodz.bas.text.lop.fsm;

public class CharCategory extends _CharSet {

    private final int category;

    public CharCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean contains(int x) {
        int type = Character.getType(x);
        return type == category;
    }

    @Override
    public int intersects(CharSet that) {
        if (that.isAbstract())
            if (that instanceof CharCategory) {
                if (category == ((CharCategory) that).category)
                    return EQUALS;
            } else
                return UNKNOWN;
        int thatBegin = that.getBegin();
        int thatEnd = that.getEnd();
        int n = 0;
        for (int i = thatBegin; i < thatEnd; i++)
            if (contains(i))
                n++;
        if (n == 0)
            return NONE;
        if (n == thatEnd - thatBegin)
            return ALL_THAT;
        return PARTIAL;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharCategory)
            return category == ((CharCategory) obj).category;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 0x564af286 ^ category;
        return hash;
    }

    @Override
    public String toString() {
        return "[[:category-" + category + ":]]";
    }

}
