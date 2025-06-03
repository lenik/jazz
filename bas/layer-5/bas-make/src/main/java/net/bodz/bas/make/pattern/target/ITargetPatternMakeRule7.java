package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule7;

public interface ITargetPatternMakeRule7<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
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
        extends ITargetPatternLikeMakeRule7<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, U1s, U1K, U2s, U2K, U3s, U3K, U4s, U4K, U5s, U5K, U6s, U6K, U7s, U7K, T, TT, U1, U1T, U2, U2T, U3, U3T, U4, U4T, U5, U5T, U6, U6T, U7, U7T>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedTarget<?, ?, ?, ?>[] getInputs() {
        return new IParameterizedTarget[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5(), getInput6(), getInput7() };
    }

}
