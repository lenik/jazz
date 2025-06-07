package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule3;

public interface IDataTypedKeyPatternMakeRule3<Tp extends IDataTypedKeyPattern<Param, K, TT>, Param, K, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT, //
        W extends IKeyData<WK, WT>, WK, WT> //
        extends IKeyPatternLikeMakeRule3<Tp, Param, K, IDataTypedParameterizedKey<?, ?, ?>, Us, Vs, Ws, T, TT, U, UK, UT, V, VK, VT, W, WK, WT>,
                IDataTypedKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1(), getInput2(), getInput3() };
    }

}
