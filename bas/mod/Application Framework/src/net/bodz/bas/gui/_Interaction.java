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
    public <K> K choice(String title, Map<K, ?> candidates, K initial) {
        return choice(title, title, candidates, initial);
    }

    @Override
    public int choice(String title, List<?> candidates, int initial) {
        return choice(title, title, candidates, initial);
    }

    @Override
    public int choice(String title, Object detail, List<?> candidates,
            int initial) {
        return choice(title, detail, list2map(candidates), initial);
    }

    @Override
    public <K> K[] choices(String title, Map<K, ?> candidates, K... initial) {
        return choices(title, title, candidates, initial);
    }

    @Override
    public int[] choices(String title, List<?> candidates, int... initial) {
        Integer[] choices = choices(title, list2map(candidates), box(initial));
        return unbox(choices);
    }

    @Override
    public int[] choices(String title, Object detail, List<?> candidates,
            int... initial) {
        Integer[] choices = choices(title, detail, list2map(candidates),
                box(initial));
        return unbox(choices);
    }

    private Map<Integer, Object> list2map(List<?> list) {
        assert list != null;
        IndexMap<Object> indexMap = new IndexMap<Object>(list.size());
        for (int i = 0; i < list.size(); i++) {
            Object cand = list.get(i);
            indexMap.put(i, cand);
        }
        return indexMap;
    }

    private Integer[] box(int[] ints) {
        if (ints == null)
            return null;
        Integer[] boxed = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++)
            boxed[i] = ints[i];
        return boxed;
    }

    private int[] unbox(Integer[] boxed) {
        if (boxed == null)
            return null;
        int[] ints = new int[boxed.length];
        for (int i = 0; i < boxed.length; i++)
            ints[i] = boxed[i];
        return ints;
    }

}
