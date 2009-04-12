package net.bodz.bas.rt;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        return prompt(title, title, ""); //$NON-NLS-1$
    }

    @Override
    public String prompt(String title, String initial) {
        return prompt(title, title, initial);
    }

    @Override
    public String prompt(String title, Object detail) {
        return prompt(title, detail, ""); //$NON-NLS-1$
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
    public <K> K choice(String title, Map<K, ?> candidates) {
        return choice(title, title, candidates, null);
    }

    @Override
    public <K> K choice(String title, Map<K, ?> candidates, K initial) {
        return choice(title, title, candidates, initial);
    }

    @Override
    public <K> K choice(String title, Object detail, Map<K, ?> candidates) {
        return choice(title, detail, candidates, null);
    }

    @Override
    public int choice(String title, List<?> candidates) {
        return choice(title, title, candidates, -1);
    }

    @Override
    public int choice(String title, List<?> candidates, int initial) {
        return choice(title, title, candidates, initial);
    }

    @Override
    public int choice(String title, Object detail, List<?> candidates) {
        Integer index = choice(title, detail, list2map(candidates), -1);
        return index == null ? -1 : index;
    }

    @Override
    public int choice(String title, Object detail, List<?> candidates,
            int initial) {
        Integer index = choice(title, detail, list2map(candidates), initial);
        return index == null ? -1 : index;
    }

    @Override
    public <K> Set<K> choices(String title, Map<K, ?> candidates, K... initial) {
        return choices(title, title, candidates, initial);
    }

    @Override
    public int[] choices(String title, List<?> candidates, int... initial) {
        Set<Integer> choices = choices(title, list2map(candidates),
                box(initial));
        return unbox(choices);
    }

    @Override
    public int[] choices(String title, Object detail, List<?> candidates,
            int... initial) {
        Set<Integer> choices = choices(title, detail, list2map(candidates),
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

    private int[] unbox(Set<Integer> boxed) {
        if (boxed == null)
            return null;
        int size = boxed.size();
        int[] ints = new int[size];
        int i = 0;
        for (Integer val : boxed)
            ints[i++] = val;
        return ints;
    }

}
