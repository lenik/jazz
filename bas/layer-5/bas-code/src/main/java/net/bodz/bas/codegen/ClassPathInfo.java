package net.bodz.bas.codegen;

import java.nio.file.Path;

import net.bodz.bas.t.tuple.QualifiedName;

public class ClassPathInfo
        implements
            IClassPathInfo {

    public final QualifiedName qName;
    public final String packageName;
    public final String name;

    public final Path baseDir;

    public final String javaDir;
    public final String testJavaDir;

    public final String resourceDir;
    public final String testResourceDir;

    public final String scriptDir;
    public final String testScriptDir;

    public ClassPathInfo(QualifiedName qName, Path baseDir, String javaDir, String testJavaDir, String resourceDir,
            String testResourceDir, String scriptDir, String testScriptDir) {
        this.qName = qName;
        this.packageName = qName.packageName;
        this.name = qName.name;

        this.baseDir = baseDir;

        this.javaDir = javaDir;
        this.resourceDir = resourceDir;
        this.scriptDir = scriptDir;

        this.testJavaDir = testJavaDir;
        this.testResourceDir = testResourceDir;
        this.testScriptDir = testScriptDir;
    }

    public static class Builder {

        QualifiedName qName;
        Path baseDir;
        String javaDir;
        String testJavaDir;
        String resourceDir;
        String testResourceDir;
        String scriptDir;
        String testScriptDir;

        public Builder qName(QualifiedName qName) {
            this.qName = qName;
            return this;
        }

        public Builder qName(String packageName, String name) {
            this.qName = new QualifiedName(packageName, name);
            return this;
        }

        public Builder baseDir(Path baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public Builder javaDir(String javaDir) {
            this.javaDir = javaDir;
            return this;
        }

        public Builder testJavaDir(String testJavaDir) {
            this.testJavaDir = testJavaDir;
            return this;
        }

        public Builder resourceDir(String resourceDir) {
            this.resourceDir = resourceDir;
            return this;
        }

        public Builder testResourceDir(String testResourceDir) {
            this.testResourceDir = testResourceDir;
            return this;
        }

        public Builder scriptDir(String scriptDir) {
            this.scriptDir = scriptDir;
            return this;
        }

        public Builder testScriptDir(String testScriptDir) {
            this.testScriptDir = testScriptDir;
            return this;
        }

        public Builder maven(Path baseDir) {
            this.baseDir = baseDir;
            this.javaDir = "src/main/java";
            this.resourceDir = "src/main/resources";
            this.testJavaDir = "src/test/java";
            this.testResourceDir = "src/test/resources";
            this.scriptDir = null;
            this.testScriptDir = null;
            return this;
        }

        public Builder npm(Path packageDir) {
            this.baseDir = packageDir;
            this.javaDir = null;
            this.resourceDir = null;
            this.testJavaDir = null;
            this.testResourceDir = null;
            this.scriptDir = "src";
            this.testScriptDir = "test";
            return this;
        }

        public ClassPathInfo build() {
            ClassPathInfo o = new ClassPathInfo(//
                    qName, baseDir, //
                    javaDir, testJavaDir, //
                    resourceDir, testResourceDir, //
                    scriptDir, testScriptDir);
            return o;
        }

    }

    public ClassPathInfo child(String childQName) {
        return child(childQName, this.javaDir, this.resourceDir);
    }

    public ClassPathInfo child(String childQName, String javaDir) {
        return child(childQName, javaDir, javaDir);
    }

    public ClassPathInfo child(String childQName, String javaDir, String resourceDir) {
        QualifiedName qName = this.qName.child(childQName);
        ClassPathInfo o = new ClassPathInfo(//
                qName, baseDir, //
                javaDir, testJavaDir, //
                resourceDir, testResourceDir, //
                scriptDir, testScriptDir);
        return o;
    }

    @Override
    public Path getPreferredDir(String extension, boolean test) {
        Path parent;
        switch (extension) {
        case "java":
            parent = baseDir.resolve(test ? testJavaDir : javaDir);
            break;

        case "ts":
        case "vue":
            parent = baseDir.resolve(test ? testScriptDir : scriptDir);
            break;

        default:
            parent = baseDir.resolve(test ? testResourceDir : resourceDir);
        }
        return parent;
    }

    @Override
    public Path getPreferredFile(String extension, boolean test) {
        Path parent = getPreferredDir(extension, test);
        Path path = qName.toPath(parent, extension);
        return path;
    }

    @Override
    public QualifiedName getQName() {
        return qName;
    }

    @Override
    public Path getBaseDir() {
        return baseDir;
    }

    @Override
    public String getJavaDir() {
        return javaDir;
    }

    @Override
    public String getResourceDir() {
        return resourceDir;
    }

    @Override
    public String getTestJavaDir() {
        return testJavaDir;
    }

    @Override
    public String getTestResourceDir() {
        return testResourceDir;
    }

    @Override
    public String getScriptDir() {
        return scriptDir;
    }

    @Override
    public String getTestScriptDir() {
        return scriptDir;
    }

    @Override
    public String toString() {
        return qName.getFullName() + "(" + baseDir + ")";
    }

}
