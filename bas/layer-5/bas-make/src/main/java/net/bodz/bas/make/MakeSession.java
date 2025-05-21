package net.bodz.bas.make;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class MakeSession
        implements IMakeSession {

    Map<Object, IDataEntry<?, ?>> keyMap = new HashMap<>();

    ListMap<IDataEntry<?, ?>, IMakeRule<?>> rulesMap = new ListMap<>();
    ListMap<IKeyPattern<?, ?>, IPatternMakeRule<?, ?, ?>> patternRulesMap = new ListMap<>();

    // Class<K> => IKeyParameter<?, K>
    ListMap<Class<?>, IKeyPattern<?, ?>> patternsMap = new ListMap<>();

    @Override
    public void addData(@NotNull IDataEntry<?, ?> entry) {
        keyMap.put(entry.getKey(), entry);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> IDataEntry<K, ?> getData(@NotNull K key) {
        return (IDataEntry<K, ?>) keyMap.get(key);
    }

    // rules

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IDataEntry<?, ?>> List<IMakeRule<T>> getRules(T target) {
        return (List<IMakeRule<T>>) (Object) rulesMap.getOrEmpty(target);
    }

    @Override
    public <T extends IDataEntry<?, ?>> void addRule(@NotNull T target, @NotNull IMakeRule<T> rule) {
        rulesMap.addToList(target, rule);
    }

    @Override
    public <T extends IDataEntry<?, ?>> void addRule(@NotNull T target, IDataEntry<?, ?>[] inputs, @NotNull MakeFunction<T> fn) {
        rulesMap.addToList(target, //
                SimpleMakeRule.<T>builder()//
                        .input(inputs)//
                        .make(fn).build());
    }

    @Override
    public <T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT> //
    void addRule(@NotNull T target, U input1, @NotNull MakeFunction1<TT, UT> fn) {
        rulesMap.addToList(target, //
                SimpleMakeRule1.<T, TK, TT, U, UK, UT>builder() //
                        .input(input1) //
                        .fn(fn).build());
    }

    @Override
    public <T extends IDataEntry<TK, TT>, TK, TT, U extends IDataEntry<UK, UT>, UK, UT, V extends IDataEntry<VK, VT>, VK, VT> //
    void addRule(@NotNull T target, U input1, V input2, @NotNull MakeFunction2<TT, UT, VT> fn) {
        rulesMap.addToList(target, //
                SimpleMakeRule2.<T, TK, TT, U, UK, UT, V, VK, VT>builder() //
                        .input(input1, input2) //
                        .fn(fn).build());
    }

    // parameterized rules

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T extends IKeyPattern<?, K>, K, D extends IDataEntry<K, ?>> //
    List<IPatternMakeRule<T, K, D>> getPatternRules(IKeyPattern<?, ?> pattern) {
        return (List<IPatternMakeRule<T, K, D>>) (Object) patternRulesMap.get(pattern);
    }

    @Override
    public <T extends IKeyPattern<?, K>, K, D extends IDataEntry<K, ?>> //
    void addPatternRule(@NotNull T pattern, @NotNull IPatternMakeRule<T, K, D> rule) {
        patternRulesMap.addToList(pattern, rule);
    }

    public <Tp extends IKeyPattern<Param, K>, Param, K, Us extends IParameterizedKeys<Param, UK>, UK, //
            T extends IDataEntry<K, TT>, TT, U extends IDataEntry<UK, UT>, UT> //
    void addPatternRule(@NotNull Tp pattern, @NotNull Us input1s, @NotNull CompileFunction1<T, K, TT, U, UK, UT> fn) {
        patternRulesMap.addToList(pattern, SimplePatternMakeRule1.<Tp, Param, K, Us, UK, T, TT, U, UT>builder()//
                .pattern(pattern) //
                .input(input1s)//
                .fn(fn).build());
    }

    // make

    public <T extends IDataEntry<TK, TT>, TK, TT> void make(T target)
            throws MakeException {

        if (target.exists())
            return;

//        TK targetKey = target.getKey();
        Class<TK> targetKeyType = target.getKeyType();

        for (IKeyPattern<?, ?> pattern : patternRulesMap.keySet()) {
            Class<?> patternKeyType = pattern.getKeyType();
            if (patternKeyType.isAssignableFrom(targetKeyType)) {
//                Object param = parameter.resolve(targetKey);
                for (IPatternMakeRule<IKeyPattern<?, Object>, Object, IDataEntry<Object, ?>> patternRule : getPatternRules(pattern)) {
                    @SuppressWarnings("unchecked")
                    MakeRuleInstance<T> instance = (MakeRuleInstance<T>) patternRule.compile((IDataEntry<Object, ?>) target, this);
                    if (instance == null)
                        continue;
                    IMakeRule<T> rule = instance.getRule();
                    rule.make(target, rule.getInputs());
                    return;
                }
            }
        }

        for (IMakeRule<T> rule : getRules(target)) {
            if (rule.canMake()) {
                for (IDataEntry<?, ?> input : rule.getInputs()) {
                    make(input);
                }
                rule.make(target, rule.getInputs());
                return;
            }
        }
        throw new MakeException("can't find a make rule for " + target);
    }

}
