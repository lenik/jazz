package net.bodz.bas.ant;

import net.bodz.bas.io.term.LogTerm;

import com.sun.jmx.snmp.tasks.Task;

public class TaskLogTerm extends LogTerm {

    private final Task task;

    public TaskLogTerm(Task task) {
        if (task == null)
            throw new NullPointerException("project"); 
        this.task = task;
    }

    @Override
    public ITerminal filter(final int level) {
        return new BufferedTerminal() {
            @Override
            public void _p(String s) {
                int msgLevel = getAntLevel(level);
                task.log(s, msgLevel);
            }
        };
    }

    public static int getAntLevel(int level) {
        if (level <= ERROR)
            return Project.MSG_ERR;
        if (level <= WARN)
            return Project.MSG_WARN;
        if (level <= INFO)
            return Project.MSG_INFO;
        if (level <= DETAIL)
            return Project.MSG_VERBOSE;
        return Project.MSG_DEBUG;
    }

}
