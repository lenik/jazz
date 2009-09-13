package net.bodz.bas.functors;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;
import net.bodz.bas.lang.a.ChainOrder;
import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;

public abstract class Functor2<T> implements IFunctor<T>, Groupable<T> {

    public IFunctor<?> getOuter() {
        return null;
    }

    public void setOuter(IFunctor<?> outer) {
        throw new UnsupportedOperationException();
    }

    public void evalVoid() throws Control, EvalException {
        eval();
    }

    public byte evalByte() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).byteValue();
    }

    public short evalShort() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).shortValue();
    }

    public int evalInt() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).intValue();
    }

    public long evalLong() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).longValue();
    }

    public float evalFloat() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).floatValue();
    }

    public double evalDouble() throws Control, EvalException, ClassCastException {
        return ((Number) eval()).doubleValue();
    }

    public boolean evalBoolean() throws Control, EvalException, ClassCastException {
        return ((Boolean) eval()).booleanValue();
    }

    public char evalChar() throws Control, EvalException, ClassCastException {
        return ((Character) eval()).charValue();
    }

    @Override
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    public IFunctor<?> reduce() throws FunctorException {
        return this;
    }

    @Override
    @OverrideOption(chain = ChainUsage.PREFERRED, order = ChainOrder.TAIL)
    public void validate() throws ValidationException {
    }

    @Override
    public Groupable<T> concat(IFunctor<T> functor) {
        return new Sequence<T>(functor);
    }

    @Override
    public Groupable<T> label(Object labelKey) {
        return new Sequence<T>().label(labelKey);
    }

    /**
     * Structure reduced
     */
    protected static final int REDUCED   = 0x00000001;

    /**
     * Validated
     */
    protected static final int VALIDATED = 0x00000010;

    /**
     * Evaluated
     */
    protected static final int EVALUATED = 0x00000100;

    /**
     * Don't cache result of validate and eval
     * 
     * @see net.bodz.commons.lang.Transient
     */
    protected static final int TRANSIENT = 0x00001000;

    protected static final int OUTGO     = 0x00002000;

    /**
     * Closed functors don't affect the environment, context, etc. <br>
     * so they are not "dirty", and can be safely removed. <br>
     * This is useful in functor-reduce archetecture.
     */
    protected static final int CLOSED    = 0x00010000;

    protected abstract int getFlags();

    protected abstract void setFlags(int flags);

    protected boolean testFlagBits(int bits) {
        return (getFlags() & bits) != 0;
    }

    protected void setFlagBits(int bits) {
        setFlags(getFlags() | bits);
    }

    protected void setFlagBits(int bits, int mask) {
        setFlags(getFlags() & ~mask);
        setFlags(getFlags() | bits);
    }

    protected final void setFlagBits(int mask, boolean setOrClear) {
        if (setOrClear)
            setFlagBits(mask);
        else
            clearFlagBits(mask);
    }

    protected void clearFlagBits(int bits) {
        setFlags(getFlags() & ~bits);
    }

    public boolean isReduced() {
        return testFlagBits(REDUCED);
    }

    public boolean isValidated() {
        return testFlagBits(VALIDATED);
    }

    public boolean isEvaluated() {
        return testFlagBits(EVALUATED);
    }

    public boolean isTransient() {
        return testFlagBits(TRANSIENT);
    }

    public boolean isOutgo() {
        return testFlagBits(OUTGO);
    }

    public boolean isClosed() {
        return testFlagBits(CLOSED);
    }

}
