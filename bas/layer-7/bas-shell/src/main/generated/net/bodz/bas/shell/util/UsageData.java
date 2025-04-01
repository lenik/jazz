package net.bodz.bas.shell.util;

public class UsageData {

    final Object source;
    String description;
    public int count;

    public UsageData(Object source) {
        this.source = source;
    }

    public UsageData(Object source, String description) {
        this.source = source;
        this.description = description;
    }

    public UsageData(Object source, String description, int count) {
        this.source = source;
        this.description = description;
        this.count = count;
    }

    public Object getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
