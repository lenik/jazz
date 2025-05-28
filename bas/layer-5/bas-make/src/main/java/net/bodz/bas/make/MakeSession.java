package net.bodz.bas.make;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.make.plan.IMakeNode;
import net.bodz.bas.make.plan.MutableMakeNode;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.TypeListPmap;

public class MakeSession
        implements IMakeSession {

    MakeRules rules;

    Map<Object, IKeyData<?, ?>> keyMap = new HashMap<>();
    TypeListPmap<IKeyData<?, ?>> keyTypeListMap = new TypeListPmap<>();
    TypeListPmap<IKeyData<?, ?>> dataTypeListMap = new TypeListPmap<>();

    public MakeSession(@NotNull MakeRules rules) {
        this.rules = rules;
    }

    @NotNull
    @Override
    public MakeRules getRules() {
        return rules;
    }

    @Override
    public void addData(@NotNull IKeyData<?, ?> keyData) {
        keyMap.put(keyData.getKey(), keyData);
    }

    @Override
    public <K> IKeyData<K, ?> getData(@NotNull K key) {
        @SuppressWarnings("unchecked")
        IKeyData<K, ?> data = (IKeyData<K, ?>) keyMap.get(key);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<IKeyData<?, T>> findData(@NotNull Class<T> dataType, boolean join) {
        if (join) {
            return (List<IKeyData<?, T>>) (List<?>) dataTypeListMap.getOrEmpty(dataType);
        } else {
            return (List<IKeyData<?, T>>) (List<?>) dataTypeListMap.joinConcatenated(dataType);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> List<IKeyData<K, ?>> findDataByKeyType(@NotNull Class<K> keyType, boolean join) {
        if (join) {
            return (List<IKeyData<K, ?>>) (List<?>) keyTypeListMap.getOrEmpty(keyType);
        } else {
            return (List<IKeyData<K, ?>>) (List<?>) keyTypeListMap.joinConcatenated(keyType);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, T> List<IKeyData<K, T>> resolve(@NotNull IDataTypedKey<K, T> dataTypedKey) {
        if (dataTypedKey instanceof IKeyData<?, ?>)
            return Collections.singletonList((IKeyData<K, T>) dataTypedKey);

        K key = dataTypedKey.getKey();
        IKeyData<K, T> data = (IKeyData<K, T>) getData(key);
        if (data != null)
            return Collections.singletonList(data);

        List<IKeyData<K, T>> list = new ArrayList<>();
        for (IKeyData<?, ? extends T> _data : findData(dataTypedKey.getDataType(), true)) {
            list.add((IKeyData<K, T>) _data);
        }
        for (IKeyData<? extends K, ?> _data : findDataByKeyType(dataTypedKey.getKeyType(), true)) {
            list.add((IKeyData<K, T>) _data);
        }
        return list;
    }

    @Override
    @NotNull
    public IKeyData<?, ?>[] resolve(@NotNull IDataTypedKey<?, ?>... inputKeys) {
        IKeyData<?, ?>[] inputs = new IKeyData<?, ?>[inputKeys.length];
        for (int i = 0; i < inputKeys.length; i++) {
            IDataTypedKey<?, ?> inputKey = inputKeys[i];
            List<? extends IKeyData<?, ?>> inputList = resolve(inputKey);
            if (inputList.isEmpty())
                throw new IllegalUsageException("can't resolve " + inputKey);
            if (inputList.size() > 1)
                throw new IllegalUsageException("ambiguous input key " + inputKey + " => " + inputList);
            IKeyData<?, ?> input = inputList.get(0);
            inputs[i] = input;
        }
        return inputs;
    }

    @Override
    public IMakeNode makeGraph(@NotNull IKeyData<?, ?> target, boolean reduce)
            throws CompileException {
        MutableMakeNode selection = MutableMakeNode.select();
        for (IMakeRule<? extends IKeyData<?, ?>> rule : getRules().makeRules(target, this)) {
            IDataTypedKey<?, ?>[] inputKeys = rule.getInputs();
            IKeyData<?, ?>[] inputs = resolve(inputKeys);

            @SuppressWarnings({ "rawtypes", "unchecked" })
            BoundRule bound = ((IMakeRule) rule).bind(target, inputs);
            MutableMakeNode runNode = MutableMakeNode.run(bound);

            for (IKeyData<?, ?> input : inputs) {
                if (input.exists())
                    continue;
                IMakeNode inputNode = makeGraph(input, true);
                if (inputNode == null)
                    throw new IllegalUsageException("dont know how to make input " + input);
                runNode.addChild(inputNode);
            }

            selection.addChild(runNode);
        }
        if (!reduce)
            return selection;
        switch (selection.getChildCount()) {
            case 0:
                return null;
            case 1:
                return selection.getChildren().get(0);
            default:
                return selection;
        }
    }

    @Override
    public <T extends IKeyData<TK, TT>, TK, TT> void make(T target)
            throws MakeException {
        if (target.exists())
            return;

        IMakeNode plan = makePlan(target);
        if (plan == null)
            throw new MakeException("can't find a make rule for " + target);

        ITreeOut out = Stdio.cout.indented();
        out.println("Make graph for " + target);
        out.enter();
        plan.dump(out);
        out.leave();

        plan.run(a -> true);
    }

    //

    @Override
    public <T extends IKeyData<?, ?>> boolean canMake(@NotNull T target) {
        return true;
    }

}
