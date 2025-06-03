package net.bodz.bas.make.pattern.dtkey;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule4;

public interface IDataTypedTarget2KeyPatternMakeRule4<Tp extends IDataTypedTarget2KeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IDataTypedParameterizedKey<Param, UK, UT>, UK, //
        Vs extends IDataTypedParameterizedKey<Param, VK, VT>, VK, //
        Ws extends IDataTypedParameterizedKey<Param, WK, WT>, WK, //
        Xs extends IDataTypedParameterizedKey<Param, XK, XT>, XK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT> //
        extends IKeyPatternLikeMakeRule4<Tp, Param, TK, IDataTypedParameterizedKey<?, ?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, T, TT, U, UT, V, VT, W, WT, X, XT>,
                IDataTypedTarget2KeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IDataTypedParameterizedKey<?, ?, ?>[] getInputs() {
        return new IDataTypedParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4() };
    }

}
