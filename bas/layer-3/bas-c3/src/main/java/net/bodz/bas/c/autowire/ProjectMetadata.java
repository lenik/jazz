package net.bodz.bas.c.autowire;

import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.bas.t.order.IPriority;

public class ProjectMetadata
        implements
            IPriority {

    private final Class<?> projectClass;
    private String name;
    private int priority;

    public ProjectMetadata(Class<?> projectClass) {
        if (projectClass == null)
            throw new NullPointerException("clazz");
        this.projectClass = projectClass;

        ProjectName aProjectName = projectClass.getAnnotation(ProjectName.class);
        assert aProjectName != null;
        name = aProjectName.value();

        Priority aPriority = projectClass.getAnnotation(Priority.class);
        if (aPriority != null)
            priority = aPriority.value();
    }

    public Class<?> getProjectClass() {
        return projectClass;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return projectClass.getCanonicalName();
    }

    public static Order ORDER = Order.INSTANCE;

    static class Order
            extends AbstractNonNullComparator<ProjectMetadata> {

        @Override
        public int compareNonNull(ProjectMetadata o1, ProjectMetadata o2) {
            int cmp = o1.priority - o2.priority;
            if (cmp != 0)
                return cmp;

            String name1 = o1.name;
            String name2 = o2.name;
            cmp = name1.compareTo(name2);
            if (cmp != 0)
                return cmp;

            name1 = o1.projectClass.getName();
            name2 = o2.projectClass.getName();
            cmp = name1.compareTo(name2);
            return cmp;
        }

        public static final Order INSTANCE = new Order();
    }

}
