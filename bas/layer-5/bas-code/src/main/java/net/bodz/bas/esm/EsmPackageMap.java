package net.bodz.bas.esm;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.codegen.QualifiedName;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.preorder.PackageMap;

public class EsmPackageMap
        extends PackageMap<EsmModule> {

    private static final long serialVersionUID = 1L;

    int localPriority;
    int localVuePriority;

    public EsmPackageMap() {
    }

    public void addModule(String packageName, EsmModule module) {
        if (packageName == null)
            throw new NullPointerException("packageName");
        if (module == null)
            throw new NullPointerException("module");
        put(packageName, module);
    }

    public EsmModule findModule(String packageName) {
        EsmModule module = meet(packageName);
        return module;
    }

    public EsmSource findSource(QualifiedName qName, String extension, QualifiedName context) {
        // don't import bare name.
        if (qName.packageName == null)
            return null;

        String path = qName.getLocalPath(extension);
        if (path == null)
            throw new IllegalUsageException("null path of qName");

        EsmModule module = findModule(qName.packageName);
        if (module != null)
            return module.source(path);

        String contextPath = context.getLocalPath();
        String href = FilePath.getRelativePath(path, contextPath);
        if (! (href.startsWith("../") || href.startsWith("./")))
            href = "./" + href;

        EsmSource source;
        if ("vue".equals(extension))
            source = EsmModule.local(localVuePriority).source(href);
        else
            source = EsmModule.local(localPriority).source(href);
        return source;
    }

}
