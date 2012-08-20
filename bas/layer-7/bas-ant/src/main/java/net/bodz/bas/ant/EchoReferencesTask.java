package net.bodz.bas.ant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

import net.bodz.bas.util.order.ComparableComparator;

public class EchoReferencesTask
        extends Task {

    @Override
    public void execute()
            throws BuildException {
        Project project = getProject();

        Hashtable<?, ?> references = project.getReferences();
        List<Object> keys = new ArrayList<Object>(references.keySet());
        Collections.sort(keys, ComparableComparator.getRawInstance());

        for (Object key : keys) {
            Object value = references.get(key);
            String s = String.format("%s = (Class %s)\n    %s", //
                    key, value == null ? "(n/a)" : value.getClass().getName(), value);
            log(s, Project.MSG_INFO);
        }
    }

}
