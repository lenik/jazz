package net.bodz.bas.gui;

import java.util.List;
import java.util.Map;

import net.bodz.bas.types.IndexMap;

public abstract class _Interaction implements Interaction {

    @Override
    public void alert(String title) {
        alert(title, title);
    }

    @Override
    public boolean confirm(String title) {
        return confirm(title, title);
    }

    @Override
    public String prompt(String title) {
        return prompt(title, title, "");
    }

    @Override
    public String prompt(String title, String initial) {
        return prompt(title, title, initial);
    }

    @Override
    public String prompt(String title, Object detail) {
        return prompt(title, detail, "");
    }

    @Override
    public String prompt(String title, Object detail, String initial) {
        return prompt(title, detail, String.class, initial);
    }

    @Override
    public <T> T prompt(String title, Class<T> type, T initial) {
        return prompt(title, title, type, initial);
    }

    @Override
    public int choice(String title, List<?> candidates) {
        return choice(title, title, candidates);
    }

    @Override
    public <K> K choice(String title, Map<K, ?> candidates) {
        return choice(title, title, candidates);
    }

    @Override
    public int choice(String title, Object detail, List<?> candidates) {
        IndexMap<Object> indexMap = new IndexMap<Object>();
        return choice(title, detail, indexMap);
    }

}
