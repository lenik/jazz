package net.bodz.lily.gen;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public abstract class JavaSourceBuilder<model_t> {

    protected String packageName;
    // String fileName;

    protected ImportedTypenames imports;
    protected ITreeOut out;

    public JavaSourceBuilder(String packageName) {
        this.packageName = packageName;
        // this.fileName = fileName;
    }

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        ITreeOut root = TreeOutImpl.from(buf);
        build(root, model);
        return buf.toString();
    }

    public synchronized void build(ITreeOut rootOut, model_t model) {
        rootOut.println("package " + packageName + ";");
        rootOut.println();

        imports = new ImportedTypenames();

        BCharOut body = new BCharOut();
        out = TreeOutImpl.from(body);
        buildClassBody(model);

        imports.dump(rootOut);
        rootOut.println();

        rootOut.print(body);
        rootOut.flush();
    }

    protected abstract void buildClassBody(model_t model);

}
