package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public abstract class JavaGen__xml
        extends JavaGenFileType {

    public JavaGen__xml(JavaGenProject project, ClassPathInfo name) {
        super(project, name);
    }

    @Override
    protected String getExtension() {
        return XML;
    }

    @Override
    public void build(ITreeOut out, ITableViewMetadata model) {
        buildXml(out, model);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata model) {
        throw new NotImplementedException();
    }

}