package net.bodz.bas.codegen;

import java.io.File;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IClassPathInfo {

    QualifiedName getQName();

    File getBaseDir();

    String getJavaDir();

    String getResourceDir();

    String getTestJavaDir();

    String getTestResourceDir();

    String getScriptDir();

    String getTestScriptDir();

    File getPreferredDir(String extension, boolean test);

    File getPreferredFile(String extension, boolean test);

}
