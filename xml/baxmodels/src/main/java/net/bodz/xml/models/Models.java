package net.bodz.xml.models;

import java.io.File;

import net.bodz.bas.cli.util.ModulesRoot;
import net.bodz.bas.snm.EclipseProject;

/**
 * @test {@link ModelsTest}
 */
public class Models {

    private static String           modelsPackagePrefix = "net.bodz.xml.models.";

    public static final ModulesRoot modelsRoot;
    static {
        File projectDir = EclipseProject.findProjectBase(Models.class);
        File rootDir = new File(projectDir, "models");
        if (!rootDir.isDirectory())
            throw new Error("Can't find models-root");
        modelsRoot = new ModulesRoot(rootDir);
    }

    /**
     * Example:
     * <p>
     * net.bodz.xml.models.pdb.subs[.impl] => pdb/subs
     */
    public static String getModelPath(Package _package) {
        String p = _package.getName();
        if (!p.startsWith(modelsPackagePrefix))
            throw new IllegalArgumentException("Not a class for xml.models: " + p);
        String modelName = p.substring(modelsPackagePrefix.length());
        if (modelName.endsWith(".impl"))
            modelName = modelName.substring(0, modelName.length() - 5);
        if (modelName.isEmpty())
            throw new IllegalArgumentException("empty package: " + modelName);
        return modelName.replace('.', '/');
    }

    public static File findModelHome(String modelPath) {
        return modelsRoot.findabc(modelPath);
    }

}
