package net.bodz.bas.code.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyProject
        extends CodeProject {

    public MyProject(Path projectDir, ICodeModule... modules) {
        super(projectDir);
        ResourceMap map = ResourceMap.fromListing(getClass());
        addModule(map);
        for (ICodeModule m : modules)
            addModule(m);
    }

    public static void main(String[] args)
            throws IOException {
        Path dir = Files.createTempDirectory("myproj");
        MyProject project = new MyProject(dir);
        project.prepare();
        System.out.println(dir);
    }

}
