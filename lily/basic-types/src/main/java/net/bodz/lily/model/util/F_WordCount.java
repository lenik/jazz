package net.bodz.lily.model.util;

import java.util.Map.Entry;

public class F_WordCount
        implements Entry<String, String> {

    String word;
    long count;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String getKey() {
        return word;
    }

    @Override
    public String getValue() {
        return word;
    }

    @Override
    public String setValue(String value) {
        return null;
    }

}
