package net.bodz.bas.code.project;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CodeProject {

    static final Logger logger = LoggerFactory.getLogger(CodeProject.class);

    Path projectDir;

    List<ICodeModule> modules = new ArrayList<>();

    public CodeProject(Path projectDir, ICodeModule... modules) {
        this.projectDir = projectDir;
        Collections.addAll(this.modules, modules);
    }

    public Path getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(Path projectDir) {
        this.projectDir = projectDir;
    }

    public List<ICodeModule> getModules() {
        return modules;
    }

    public void addModule(ICodeModule module) {
        if (module == null)
            throw new NullPointerException("module");
        modules.add(module);
    }

    public void prepare()
            throws IOException {
        for (ICodeModule module : modules) {
            module.prepareFiles(projectDir);
        }
    }

}
