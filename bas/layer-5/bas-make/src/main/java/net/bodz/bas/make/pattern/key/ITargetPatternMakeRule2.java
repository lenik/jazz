package net.bodz.bas.make.pattern.key;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedKeys;
import net.bodz.bas.make.pattern.template.IKeyPatternLikeMakeRule2;

public interface ITargetPatternMakeRule2<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedKeys<Param, UK>, UK, //
        Vs extends IParameterizedKeys<Param, VK>, VK, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UT, //
        V extends IKeyData<VK, VT>, VT> //
        extends IKeyPatternLikeMakeRule2<Tp, Param, TK, IParameterizedKeys<?, ?>, Us, UK, Vs, VK, T, TT, U, UT, V, VT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedKeys<?, ?>[] getInputs() {
        return new IParameterizedKeys[] { getInput1(), getInput2() };
    }

}
