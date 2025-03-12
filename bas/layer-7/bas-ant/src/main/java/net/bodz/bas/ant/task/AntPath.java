package net.bodz.bas.ant.task;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

public class AntPath
        extends Path {

    public AntPath(Project p, String path) {
        super(p, path);
    }

    public AntPath(Project project) {
        super(project);
    }

}
