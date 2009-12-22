package net.bodz.bas.ant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import net.bodz.bas.collection.comparators.Comparators;
import net.bodz.bas.nls.AppNLS;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

public class EchoReferencesTask extends Task {

    @Override
    public void execute() throws BuildException {
        Project project = getProject();

        Hashtable<?, ?> references = project.getReferences();
        List<Object> keys = new ArrayList<Object>(references.keySet());
        Collections.sort(keys, Comparators.STD);

        for (Object key : keys) {
            Object value = references.get(key);
            String s = String
                    .format(
                            AppNLS.getString("EchoReferencesTask.entry"), // //$NON-NLS-1$
                            key,
                            value == null ? AppNLS.getString("EchoReferencesTask.nullEntryValue") : value.getClass().getName(), value); //$NON-NLS-1$
            log(s, Project.MSG_INFO);
        }
    }

}
