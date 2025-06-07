package net.bodz.bas.make.pattern.target;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IParameterizedTarget;
import net.bodz.bas.make.pattern.template.ITargetPatternLikeMakeRule2;

public interface ITargetPatternMakeRule2<Tp extends ITargetPattern<Param, T, TK, TT>, Param, TK, //
        Us extends IParameterizedTarget<Param, U, UK, UT>, //
        Vs extends IParameterizedTarget<Param, V, VK, VT>, //
        T extends IKeyData<TK, TT>, TT, //
        U extends IKeyData<UK, UT>, UK, UT, //
        V extends IKeyData<VK, VT>, VK, VT> //
        extends ITargetPatternLikeMakeRule2<Tp, Param, TK, IParameterizedTarget<?, ?, ?, ?>, Us, Vs, T, TT, U, UK, UT, V, VK, VT>,
                ITargetPatternMakeRule<Tp, Param, TK, T, TT> {

    @Override
    default IParameterizedTarget<?, ?, ?, ?>[] getInputs() {
        return new IParameterizedTarget[] { getInput1(), getInput2() };
    }

}
