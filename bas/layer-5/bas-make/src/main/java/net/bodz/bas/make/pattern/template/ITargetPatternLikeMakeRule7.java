package net.bodz.bas.make.pattern.template;

import net.bodz.bas.make.BoundRule;
import net.bodz.bas.make.CompileException;
import net.bodz.bas.make.IDataBinding;
import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.fn.IMakeable7;
import net.bodz.bas.make.fn.MakeFunction;
import net.bodz.bas.make.fn.SimpleMakeRule7;
import net.bodz.bas.meta.decl.NotNull;

public interface ITargetPatternLikeMakeRule7<Tp extends ITargetPatternLike<Param, T, TK, TT>, Param, TK, //
        Keys extends IParameterizedTarget<?, ?, ?, ?>, //
        U1s extends IParameterizedTarget<Param, U1, U1K, U1T>, U1K, //
        U2s extends IParameterizedTarget<Param, U2, U2K, U2T>, U2K, //
        U3s extends IParameterizedTarget<Param, U3, U3K, U3T>, U3K, //
        U4s extends IParameterizedTarget<Param, U4, U4K, U4T>, U4K, //
        U5s extends IParameterizedTarget<Param, U5, U5K, U5T>, U5K, //
        U6s extends IParameterizedTarget<Param, U6, U6K, U6T>, U6K, //
        U7s extends IParameterizedTarget<Param, U7, U7K, U7T>, U7K, //
        T extends IKeyData<TK, TT>, TT, //
        U1 extends IKeyData<U1K, U1T>, U1T, //
        U2 extends IKeyData<U2K, U2T>, U2T, //
        U3 extends IKeyData<U3K, U3T>, U3T, //
        U4 extends IKeyData<U4K, U4T>, U4T, //
        U5 extends IKeyData<U5K, U5T>, U5T, //
        U6 extends IKeyData<U6K, U6T>, U6T, //
        U7 extends IKeyData<U7K, U7T>, U7T> //
        extends ITargetPatternLikeMakeRule<Tp, Param, TK, Keys, T, TT> {

    @Override
    default int getPriority() {
        return 0;
    }

    U1s getInput1();

    U2s getInput2();

    U3s getInput3();

    U4s getInput4();

    U5s getInput5();

    U6s getInput6();

    U7s getInput7();

    @Override
    default BoundRule<T, TK, TT> compile(@NotNull T target, @NotNull IDataBinding binding)
            throws CompileException {
        Tp pattern = getPattern();
        @SuppressWarnings("unchecked")
        U1s input1s = (U1s) getInputs()[0];
        @SuppressWarnings("unchecked")
        U2s input2s = (U2s) getInputs()[1];
        @SuppressWarnings("unchecked")
        U3s input3s = (U3s) getInputs()[2];
        @SuppressWarnings("unchecked")
        U4s input4s = (U4s) getInputs()[3];
        @SuppressWarnings("unchecked")
        U5s input5s = (U5s) getInputs()[4];
        @SuppressWarnings("unchecked")
        U6s input6s = (U6s) getInputs()[5];
        @SuppressWarnings("unchecked")
        U7s input7s = (U7s) getInputs()[6];

        Param param = pattern.match(target);
        if (param == null)
            return null;

        U1 input1 = input1s.getTarget(param, binding);
        if (input1 == null)
            return null;

        U2 input2 = input2s.getTarget(param, binding);
        if (input2 == null)
            return null;

        U3 input3 = input3s.getTarget(param, binding);
        if (input3 == null)
            return null;

        U4 input4 = input4s.getTarget(param, binding);
        if (input4 == null)
            return null;

        U5 input5 = input5s.getTarget(param, binding);
        if (input5 == null)
            return null;

        U6 input6 = input6s.getTarget(param, binding);
        if (input6 == null)
            return null;

        U7 input7 = input7s.getTarget(param, binding);
        if (input7 == null)
            return null;

        IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn = compile(target, input1, input2, input3, input4, input5, input6, input7);
        if (fn == null)
            return null;

        SimpleMakeRule7<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T> rule = SimpleMakeRule7.<T, TK, TT, U1, U1K, U1T, U2, U2K, U2T, U3, U3K, U3T, U4, U4K, U4T, U5, U5K, U5T, U6, U6K, U6T, U7, U7K, U7T>builder()//
                .priority(this.getPriority())//
                .input(input1, input2, input3, input4, input5, input6, input7)//
                .fn(fn).build();

        BoundRule<T, TK, TT> instance = new BoundRule<>(rule, target, input1, input2, input3, input4, input5, input6, input7);
        return instance;
    }

    @Override
    default MakeFunction<T> compile(@NotNull T target, IKeyData<?, ?>... inputs)
            throws CompileException {
        @SuppressWarnings("unchecked")
        U1 input1 = (U1) inputs[0];
        @SuppressWarnings("unchecked")
        U2 input2 = (U2) inputs[1];
        @SuppressWarnings("unchecked")
        U3 input3 = (U3) inputs[2];
        @SuppressWarnings("unchecked")
        U4 input4 = (U4) inputs[3];
        @SuppressWarnings("unchecked")
        U5 input5 = (U5) inputs[4];
        @SuppressWarnings("unchecked")
        U6 input6 = (U6) inputs[5];
        @SuppressWarnings("unchecked")
        U7 input7 = (U7) inputs[6];
        IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> fn = compile(target, input1, input2, input3, input4, input5, input6, input7);
        return (t, iv) -> {
            TT tData = fn.make(input1.getData(), input2.getData(), input3.getData(), input4.getData(), input5.getData(), input6.getData(), input7.getData());
            t.setData(tData);
        };
    }

    IMakeable7<TT, U1T, U2T, U3T, U4T, U5T, U6T, U7T> compile(@NotNull T target, U1 input1, U2 input2, U3 input3, U4 input4, U5 input5, U6 input6, U7 input7)
            throws CompileException;

}
