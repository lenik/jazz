package net.bodz.bas.db.ctx;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.c.autowire.ProjectMetadata;
import net.bodz.bas.c.string.StringPart;

public class FromCurrentProjectName
        implements
            IDefaultContextIdsResolver {

    @Override
    public int getPriority() {
        return PRIORITY_SERVER_NAME;
    }

    @Override
    public Collection<String> resolveContextIds(int level) {
        List<ProjectMetadata> projects = ProjectList.INSTANCE.getTopLevelProjects();
        if (projects.isEmpty())
            return null;
        int n = projects.size();
        ProjectMetadata project = projects.get(n - 1);

        String name = project.getName();
        Set<String> ids = new LinkedHashSet<>();
        while (name != null) {
            String path = name.replace('.', '/');
            String rpnPath = RepeatPrefixNames.DEFAULT.expand(path);

            ids.add("project/" + rpnPath);
            ids.add("project/" + name);
            name = StringPart.beforeLast(name, '.');
        }
        return ids;
    }

}
