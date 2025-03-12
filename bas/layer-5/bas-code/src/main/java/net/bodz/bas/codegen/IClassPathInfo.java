package net.bodz.bas.codegen;

import java.nio.file.Path;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IClassPathInfo {

    QualifiedName getQName();

    Path getBaseDir();

    String getJavaDir();

    String getResourceDir();

    String getTestJavaDir();

    String getTestResourceDir();

    String getScriptDir();

    String getTestScriptDir();

    Path getPreferredDir(String extension, boolean test);

    Path getPreferredFile(String extension, boolean test);

}
