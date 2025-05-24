package net.bodz.bas.make.strategy;

import java.util.List;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPattern;
import net.bodz.bas.make.pattern.dtkey.IDataTypedKeyPatternMakeRule;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DataTypedKeyPatternMatch {

    ListMap<IDataTypedKeyPattern<?, ?, ?>, IDataTypedKeyPatternMakeRule<?, ?, ?, ?, ?>> rulesMap = new ListMap<>();

    @SuppressWarnings("unchecked")
    @NotNull
    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>> getRules(IDataTypedKeyPattern<?, ?, ?> pattern) {
        return (List<IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT>>) (Object) rulesMap.get(pattern);
    }

    public <Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, T extends IKeyData<K, TT>, TT> //
    void addRule(@NotNull Tp pattern, @NotNull IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> rule) {
        rulesMap.addToList(pattern, rule);
    }

}
