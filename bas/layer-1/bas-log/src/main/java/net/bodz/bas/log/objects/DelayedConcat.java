package net.bodz.bas.log.objects;

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
            assert parts != null;
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < parts.length; i++)
                buf.append(parts[i]);
            joined = buf.toString();
            parts = null;
        }
        return joined;
    }

}
