package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule5;

public interface ITargetTypedKeyPatternMakeRule5<Tp extends ITargetTypedKeyPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        Ws extends IParameterizedKey<Param, WK>, WK, //
        Xs extends IParameterizedKey<Param, XK>, XK, //
        Ys extends IParameterizedKey<Param, YK>, YK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT, //
        W extends IKeyData<WK, WT>, WT, //
        X extends IKeyData<XK, XT>, XT, //
        Y extends IKeyData<YK, YT>, YT> //
        extends IKeyPatternLikeMakeRule5<Tp, Param, TK, IParameterizedKey<?, ?>, Us, UK, Vs, VK, Ws, WK, Xs, XK, Ys, YK, T, TT, U, UT, V, VT, W, WT, X, XT, Y, YT>,
                ITargetTypedKeyPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1(), getInput2(), getInput3(), getInput4(), getInput5() };
    }

}
