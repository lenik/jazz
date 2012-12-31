package net.bodz.mda.xjdoc.contrib.maven;

import org.apache.maven.project.MavenProject;

public class ProjectDepLoader {

    public static ClassLoader getProjectLoader(MavenProject project) {
        ClassLoader loader = null;

        if (project == null)
            System.err.println("null project");
        else {
            Class<? extends MavenProject> projcls = project.getClass();
            System.err.println("Project cls: " + projcls);

            // System.err.println("Project class realm: " + loader);
            //
            // String dump = URLClassLoaders.dump(loader);
            // System.err.println("Dump: ");
            // System.err.println(dump);
        }

        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();

        return loader;
    }

}
