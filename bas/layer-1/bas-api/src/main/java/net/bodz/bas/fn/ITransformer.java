package net.bodz.bas.fn;

import net.bodz.bas.err.TransformException;

public interface ITransformer<S, T>
        extends
            ITransformerX<S, T, TransformException> {

    default <Y> ITransformer<S, Y> join(ITransformer<? super T, Y> o) {
        return new ConjunctTransformer<S, Y>(this, o);
    }

    class Nop<T>
            extends AbstractTransformer<T, T> {

        @Override
        public T transform(T input)
                throws TransformException {
            return input;
        }

        static Nop<?> instance = new Nop<Object>();

        @SuppressWarnings("unchecked")
        public static <T> Nop<T> getInstance() {
            return (Nop<T>) instance;
        }

    }

}
