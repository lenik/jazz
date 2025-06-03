package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKey;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule2;

public interface IKeyPatternMakeRule2<Tp extends IKeyPattern<Param, K>, Param, K, //
        Us extends IParameterizedKey<Param, UK>, UK, //
        Vs extends IParameterizedKey<Param, VK>, VK, //
        T extends IKeyData<K, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends IKeyPatternLikeMakeRule2<Tp, Param, K, IParameterizedKey<?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>,
                IKeyPatternMakeRule<Tp, Param, K, T, TT> {

    @Override
    default IParameterizedKey<?, ?>[] getInputs() {
        return new IParameterizedKey[] { getInput1(), getInput2() };
    }

}
