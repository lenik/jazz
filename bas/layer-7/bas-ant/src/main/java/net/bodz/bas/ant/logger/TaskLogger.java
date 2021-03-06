package net.bodz.bas.ant.logger;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.SinkBasedLogger;

public class TaskLogger
        extends SinkBasedLogger {

    private final Task task;

    public TaskLogger(Task task) {
        if (task == null)
            throw new NullPointerException("project");
        this.task = task;
    }

    @Override
    public AntLogSink get(LogLevel category, int delta) {
        int antLevel = getAntLevel(category, delta);
        return new AntLogSink(antLevel);
    }

    public static int getAntLevel(LogLevel level, int delta) {
        int priority = level.getPriority() + delta;

        switch (priority) {
        case -1:
            return Project.MSG_WARN;
        case 0:
        case 1:
            return Project.MSG_INFO;
        case 2:
            return Project.MSG_VERBOSE;
        }

        if (priority <= -2)
            return Project.MSG_ERR;
        else
            return Project.MSG_DEBUG;
    }

    class AntLogSink
            extends AbstractLogSink {

        private int msgLevel;

        public AntLogSink(int msgLevel) {
            this.msgLevel = msgLevel;
        }

        @Override
        public void logMessage(Object message) {
            task.log(String.valueOf(message), msgLevel);
        }

        @Override
        public void logException(Object message, Throwable exception) {
            task.log(String.valueOf(message), exception, msgLevel);
        }

    }

}
