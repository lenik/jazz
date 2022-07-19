package net.bodz.lily.gen;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public abstract class JavaSourceBuilder<model_t> {

    protected String packageName;
    // String fileName;

    protected ImportedTypes imports;
    protected ITreeOut out;

    public JavaSourceBuilder(String packageName) {
        this.packageName = packageName;
        // this.fileName = fileName;
    }

    public void build(ITreeOut root, model_t model) {
        root.println("package " + packageName + ";");
        root.println();

        imports = new ImportedTypes();

        BCharOut body = new BCharOut();
        out = TreeOutImpl.from(body);
        buildClassBody(model);

        imports.dump(root);
        root.println();

        root.print(body);
        root.flush();
    }

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        ITreeOut root = TreeOutImpl.from(buf);
        build(root, model);
        return buf.toString();
    }

    protected abstract void buildClassBody(model_t model);

}
