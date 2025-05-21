package net.bodz.bas.make;

import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public interface IMakeSession {

    void addData(@NotNull IDataEntry<?, ?> entry);

    <K> IDataEntry<K, ?> getData(@NotNull K key);

    // rules

    @NotNull
    <T extends IDataEntry<?, ?>> List<IMakeRule<T>> getRules(T target);

    <T extends IDataEntry<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule);

    <T extends IDataEntry<?, ?>> void addRule(@NotNull T target, IDataEntry<?, ?>[] inputs, @NotNull MakeFunction<T> fn);

    <T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT> //
    void addRule(@NotNull T target, U input1, @NotNull MakeFunction1<TT, UT> fn);

    <T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT> //
    void addRule(@NotNull T target, U input1, V input2, @NotNull MakeFunction2<TT, UT, VT> fn);

    <T extends IDataEntry<TK, TT>, TK, TT> void make(T target)
            throws MakeException;

    // parameterized rules

    @NotNull
    <T extends IKeyPattern<?, K>, K, D extends IDataEntry<K, ?>> //
    List<IPatternMakeRule<T, K, D>> getPatternRules(IKeyPattern<?, ?> pattern);

    <T extends IKeyPattern<?, K>, K, D extends IDataEntry<K, ?>> //
    void addPatternRule(@NotNull T pattern, @NotNull IPatternMakeRule<T, K, D> rule);

}
