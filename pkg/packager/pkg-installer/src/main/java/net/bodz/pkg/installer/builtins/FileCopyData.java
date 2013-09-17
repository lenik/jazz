package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.RegistryData;

@RegistryData
public class FileCopyData {

    String[] list;
    long size;

    public String[] getList() {
        return list;
    }

    public void setList(String[] list) {
        this.list = list;
    }

    public long getSumSize() {
        return size;
    }

    public void setSumSize(long size) {
        this.size = size;
    }

    static final boolean verbose = false;

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (list == null)
            buf.append("null list");
        else {
            buf.append(String.format("%d entries, %d bytes", list.length, size));
            if (verbose) {
                for (int i = 0; i < Math.min(1, list.length); i++) {
                    if (i != 0)
                        buf.append(", ");
                    buf.append(list[i]);
                }
                if (list.length > 10)
                    buf.append(", ...");
            }
        }
        return buf.toString();
    }

}
