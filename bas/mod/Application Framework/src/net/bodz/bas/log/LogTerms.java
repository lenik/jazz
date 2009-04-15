package net.bodz.bas.log;

import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.io.term.Terminals;
import net.bodz.bas.lang.Caller;

public class LogTerms {

    public static LogTerm console;
    static {
        console = new LogTerm() {
            @Override
            public Terminal filter(int level) {
                if (level <= WARN)
                    return Terminals.stderr;
                return Terminals.stdout;
            }
        };
    }

    public static LogTerm get(int caller) {
        Class<?> callerClass = Caller.getCallerClass(1 + caller);
        return get(callerClass);
    }

    public static LogTerm get(String name) {
        return console;
    }

    public static LogTerm get(Class<?> clazz) {
        return get(clazz.getName());
    }

}
