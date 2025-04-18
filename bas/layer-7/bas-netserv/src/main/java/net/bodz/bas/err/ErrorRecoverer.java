package net.bodz.bas.err;

import net.bodz.bas.fn.ConsumerThrows;
import net.bodz.bas.fn.FalseRunner1;
import net.bodz.bas.fn.FunctionThrows;
import net.bodz.bas.fn.IRunner;
import net.bodz.bas.fn.IRunner1;
import net.bodz.bas.fn.RunnableThrows;
import net.bodz.bas.fn.SupplierThrows;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.meta.decl.NotNull;

public class ErrorRecoverer
        implements IRunner {

    ILogger logger;

    public ErrorRecoverer(ILogger logger) {
        this.logger = logger;
    }

    public static ErrorRecoverer byLogging(@NotNull ILogger logger) {
        return new ErrorRecoverer(logger);
    }

    @Override
    public <X extends Throwable> boolean run(RunnableThrows<X> runnable, String message) {
        try {
            runnable.run();
            return true;
        } catch (Throwable e) {
            logger.error(e, message);
            return false;
        }
    }

    public <T, X extends Throwable> IRunner1<T> run(SupplierThrows<T, X> supplier, String message) {
        try {
            T context = supplier.supply();
            return new Runner1<>(context);
        } catch (Throwable e) {
            logger.error(e, message);
            return FalseRunner1.getInstance();
        }
    }

    public class Runner1<T>
            implements IRunner1<T> {

        final T value;

        public Runner1(T value) {
            this.value = value;
        }

        @Override
        public <X extends Throwable> boolean thenFinally(ConsumerThrows<T, X> consumer, String message) {
            try {
                consumer.consume(value);
                return true;
            } catch (Throwable e) {
                logger.error(e, message);
                return false;
            }
        }

        @Override
        public <X extends Throwable> IRunner1<T> thenStep(ConsumerThrows<T, X> consumer, String message) {
            try {
                consumer.consume(value);
                return this;
            } catch (Throwable e) {
                logger.error(e, message);
                return FalseRunner1.getInstance();
            }
        }

        @Override
        public <R, X extends Throwable> IRunner1<R> then(FunctionThrows<R, T, X> function, String message) {
            try {
                R returnValue = function.apply(value);
                return new Runner1<>(returnValue);
            } catch (Throwable e) {
                logger.error(e, message);
                return FalseRunner1.getInstance();
            }
        }
    }

}
