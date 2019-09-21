package net.bodz.bas.t.factory;

public interface IFactoryX<T, X extends Throwable> {

    /**
     * @param argTypes
     *            may be <code>null</code> if not applicable. if argTypes isn't null, its length
     *            must be equals to the number of args.
     * @param args
     *            each argument must be instance of corresponding argType, or <code>null</code>.
     */
    T _create(Class<?>[] argTypes, Object... args)
            throws X;

    T create(Object... args)
            throws X;

    T create()
            throws X;

}
