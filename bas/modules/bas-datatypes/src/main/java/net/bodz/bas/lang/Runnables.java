package net.bodz.bas.lang;

public class Runnables {

    public static RunnableThrows<RuntimeException> cast(final Runnable runnable) {
        return new RunnableThrows<RuntimeException>() {
            @Override
            public void run() throws RuntimeException {
                runnable.run();
            }
        };
    }

    public static Runnable cast(final RunnableThrows<? extends Throwable> runnable) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Error e) {
                    throw e;
                } catch (RuntimeException e) {
                    throw e;
                } catch (Throwable e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        };
    }

}
