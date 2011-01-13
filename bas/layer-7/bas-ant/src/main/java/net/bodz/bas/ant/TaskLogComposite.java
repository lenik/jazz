package net.bodz.bas.ant;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.log.AbstractLogComposite;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.LogCategory;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

public class TaskLogComposite
        extends AbstractLogComposite {

    private final Task task;
    private final Map<Integer, AntLogSink> sinks;

    public TaskLogComposite(Task task) {
        if (task == null)
            throw new NullPointerException("project");
        this.task = task;
        this.sinks = new HashMap<Integer, AntLogSink>();
    }

    @Override
    public AntLogSink get(LogCategory category, int verboseLevel) {
        int antLevel = getAntLevel(category, verboseLevel);
        AntLogSink sink = sinks.get(antLevel);
        if (sink == null) {
            sink = new AntLogSink(antLevel);
            sinks.put(antLevel, sink);
        }
        return sink;
    }

    public static int getAntLevel(LogCategory category, int level) {
        if (category == LogCategory.ERROR) {
            if (level <= LEVEL_DEFAULT)
                return Project.MSG_ERR;
            else
                return Project.MSG_WARN;
        }
        if (category == LogCategory.INFO) {
            if (level <= LEVEL_CRITICAL)
                return Project.MSG_WARN;
            if (level <= LEVEL_DEFAULT)
                return Project.MSG_INFO;
            else
                return Project.MSG_VERBOSE;
        } else
            return Project.MSG_DEBUG;
    }

    class AntLogSink
            extends AbstractLogSink {

        private int msgLevel;

        public AntLogSink(int msgLevel) {
            this.msgLevel = msgLevel;
        }

        @Override
        protected void logMessage(Object message) {
            task.log(String.valueOf(message), msgLevel);
        }

        @Override
        protected void logException(Object message, Throwable exception) {
            task.log(String.valueOf(message), exception, msgLevel);
        }

    }

}
