package net.bodz.bas.gui.ia;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.collection.map.IndexMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.model.IExecutableX;

public abstract class AbstractUserInteraction
        implements IUserInteraction, II18nCapable {

    @Override
    public void alert(String title) {
        alert(title, title);
    }

    @Override
    public boolean confirm(String title) {
        return confirm(title, title);
    }

    @Override
    public int ask(String title, IProposal... proposals) {
        return ask(title, title, proposals);
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
    public int choice(String title, Object detail, List<?> candidates, int initial) {
        Integer index = choice(title, detail, list2map(candidates), initial);
        return index == null ? -1 : index;
    }

    @SafeVarargs
    @Override
    public final <K> Set<K> choices(String title, Map<K, ?> candidates, K... initial) {
        return choices(title, null, candidates, initial);
    }

    @Override
    public int[] choices(String title, List<?> candidates, int... initial) {
        Set<Integer> choices = choices(title, list2map(candidates), box(initial));
        return unbox(choices);
    }

    @Override
    public int[] choices(String title, Object detail, List<?> candidates, int... initial) {
        Set<Integer> choices = choices(title, detail, list2map(candidates), box(initial));
        return unbox(choices);
    }

    public <X extends Exception> int tryBlock(final IExecutableX<X> runnable)
            throws X {
        return tryBlock(runnable, TryBlock.INFINITE);
    }

    @Override
    public <X extends Exception> int tryBlock(final IExecutableX<X> runnable, int maxRetry)
            throws X {
        return new AbstractTryBlock(this, maxRetry, false) {
            @Override
            protected void body()
                    throws Exception {
                runnable.execute();
            }
        }._run();
    }

    @Override
    public void showBusy(Runnable runnable) {
        runnable.run();
    }

    // Utilities Functions

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
