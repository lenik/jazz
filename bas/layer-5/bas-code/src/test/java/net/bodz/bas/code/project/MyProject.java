package net.bodz.bas.code.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyProject
        extends CodeProject {

    public MyProject(File projectDir, ICodeModule... modules) {
        super(projectDir);
        ResourceMap map = ResourceMap.fromListing(getClass());
        addModule(map);
        for (ICodeModule m : modules)
            addModule(m);
    }

    public static void main(String[] args)
            throws IOException {
        File dir = Files.createTempDirectory("myproj").toFile();
        MyProject project = new MyProject(dir);
        project.prepare();
        System.out.println(dir);
    }

}
