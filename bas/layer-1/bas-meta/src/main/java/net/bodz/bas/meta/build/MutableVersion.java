package net.bodz.bas.meta.build;

import java.util.Arrays;

public class MutableVersion
        extends AbstractVersion {

    private static final long serialVersionUID = 1L;

    private final String[] sv;
    private final int[] iv;
    private String qualifier;

    public MutableVersion(String version) {
        int dash = version.indexOf('-');
        if (dash != -1) {
            qualifier = version.substring(dash + 1);
            version = version.substring(0, dash);
        }

        String[] v = version.split("\\.");

        sv = new String[v.length];
        iv = new int[v.length];

        for (int i = 0; i < v.length; i++)
            set(i, v[i]);
    }

    @Override
    public int size() {
        return sv.length;
    }

    @Override
    public String get(int index) {
        return sv[index];
    }

    public void set(int index, String val) {
        int ival = 0;
        for (int p = 0; p < val.length(); p++) {
            char ch = val.charAt(p);
            if (ch >= '0' && ch <= '9')
                ival = ival * 10 + ch - '0';
            else {
                ival = -1;
                break;
            }
        }
        sv[index] = val;
        iv[index] = ival;
    }

    @Override
    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    @Override
    public boolean isInt(int index) {
        return iv[index] != -1;
    }

    @Override
    public int getInt(int index) {
        return iv[index];
    }

    public void setInt(int index, int val) {
        iv[index] = val;
        sv[index] = String.valueOf(val);
    }

    @Override
    public int hashCode() {
        int hash = 0x157ceaea;
        hash += Arrays.hashCode(sv);
        if (qualifier != null)
            hash += qualifier.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof MutableVersion))
            return false;
        MutableVersion o = (MutableVersion) obj;
        if (!Arrays.equals(sv, o.sv))
            return false;
        if (qualifier != o.qualifier)
            if (qualifier == null || !qualifier.equals(o.qualifier))
                return false;
        return true;
    }

}
