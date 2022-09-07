package net.bodz.bas.codegen;

import java.io.File;

public class ClassPathInfo
        extends QualifiedName {

    final File baseDir;
    final String javaDir;
    final String resourceDir;

    public ClassPathInfo(String packageName, String name, File baseDir, String javaDir, String resourceDir) {
        super(packageName, name);
        this.baseDir = baseDir;
        this.javaDir = javaDir;
        this.resourceDir = resourceDir;
    }

    @Override
    public ClassPathInfo join(String relativeName) {
        return join(relativeName, this.javaDir, this.resourceDir);
    }

    public ClassPathInfo join(String relativeName, String javaDir) {
        return join(relativeName, javaDir, javaDir);
    }

    public ClassPathInfo join(String relativeName, String javaDir, String resourceDir) {
        QualifiedName qName = super.join(relativeName);
        return new ClassPathInfo(qName.packageName, qName.name, baseDir, javaDir, resourceDir);
    }

    public File toFile(String extension) {
        File parent;
        if ("java".equals(extension))
            parent = new File(baseDir, javaDir);
        else
            parent = new File(baseDir, resourceDir);
        return super.toFile(parent, extension);
    }

    public File getBaseDir() {
        return baseDir;
    }

    public String getJavaDir() {
        return javaDir;
    }

    public String getResourceDir() {
        return resourceDir;
    }

}
