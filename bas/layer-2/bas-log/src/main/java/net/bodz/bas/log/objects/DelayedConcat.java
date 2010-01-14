package net.bodz.bas.log.objects;

import net.bodz.bas.text.util.StringArray;

public class DelayedConcat {

    private Object[] parts;
    private String joined;

    public DelayedConcat(Object... array) {
        if (array == null)
            throw new NullPointerException("array");
        this.parts = array; // Arrays.asList(array);
    }

    @Override
    public String toString() {
        if (joined == null) {
            joined = StringArray.join(null, parts);
            parts = null;
        }
        return joined;
    }

}
