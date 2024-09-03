package net.bodz.bas.log;

import java.util.HashMap;
import java.util.Map;

public class ContextLoggers {

    static ThreadLocal<LoggerArray> tlsCommons = new ThreadLocal<>();

    static Map<Class<?>, ThreadLocal<LoggerArray>> classLocal //
            = new HashMap<>();

    public static LoggerArray compose(Class<?> clazz, ILogger sysLogger) {
        return LoggerArray.of("composition of " + clazz.getName(), //
                sysLogger, //
                getCommonLoggers(), //
                getLoggers(clazz));
    }

    public static LoggerArray getCommonLoggers() {
        LoggerArray loggers = tlsCommons.get();
        if (loggers == null) {
            synchronized (tlsCommons) {
                loggers = tlsCommons.get();
                if (loggers == null) {
                    loggers = new LoggerArray("common");
                    tlsCommons.set(loggers);
                }
            }
        }
        return loggers;
    }

    public static LoggerArray getLoggers(Class<?> clazz) {
        ThreadLocal<LoggerArray> threadLocal = classLocal.get(clazz);
        if (threadLocal == null) {
            synchronized (classLocal) {
                threadLocal = classLocal.get(clazz);
                if (threadLocal == null) {
                    threadLocal = new ThreadLocal<>();
                    classLocal.put(clazz, threadLocal);
                }
            }
        }

        LoggerArray loggers = threadLocal.get();
        if (loggers == null) {
            synchronized (threadLocal) {
                loggers = threadLocal.get();
                if (loggers == null) {
                    loggers = new LoggerArray("array of " + clazz.getName());
                }
            }
        }
        return loggers;
    }

    public static void addLogger(ILogger logger) {
        LoggerArray loggers = getCommonLoggers();
        loggers.addLogger(logger);
    }

    public static void removeLogger(ILogger logger) {
        LoggerArray loggers = getCommonLoggers();
        loggers.removeLogger(logger);
    }

    public static void addLogger(Class<?> clazz, ILogger logger) {
        LoggerArray loggers = getLoggers(clazz);
        loggers.addLogger(logger);
    }

    public static void removeLogger(Class<?> clazz, ILogger logger) {
        LoggerArray loggers = getLoggers(clazz);
        loggers.removeLogger(logger);
    }

    public static void run(ILogger logger, Runnable runnable) {
        addLogger(logger);
        runnable.run();
        removeLogger(logger);
    }

    public static void run(Class<?> clazz, ILogger logger, Runnable runnable) {
        addLogger(clazz, logger);
        runnable.run();
        removeLogger(clazz, logger);
    }

}
