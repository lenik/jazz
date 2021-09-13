package net.bodz.bas.code.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CodeProject {

    static final Logger logger = LoggerFactory.getLogger(CodeProject.class);

    File projectDir;

    List<ICodeModule> modules = new ArrayList<>();

    public CodeProject(File projectDir, ICodeModule... modules) {
        this.projectDir = projectDir;
        for (ICodeModule f : modules)
            this.modules.add(f);
    }

    public File getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(File projectDir) {
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
