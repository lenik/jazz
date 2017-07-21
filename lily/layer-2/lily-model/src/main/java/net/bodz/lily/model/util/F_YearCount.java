package net.bodz.lily.model.util;

import java.util.Map.Entry;

public class F_YearCount
        implements Entry<Integer, String> {

    Integer year;
    long count;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public Integer getKey() {
        return year;
    }

    @Override
    public String getValue() {
        return year == null ? "未指定" : year + "年";
    }

    @Override
    public String setValue(String value) {
        return null;
    }

}
