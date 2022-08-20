package net.bodz.lily.tool.javagen;

import net.bodz.bas.codegen.ClassPathInfo;
import net.bodz.bas.codegen.XmlSourceBuffer;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public abstract class JavaGen__java
        extends JavaGenFileType {

    public JavaGen__java(JavaGenProject project, ClassPathInfo pathInfo) {
        super(project, pathInfo);
    }

    @Override
    protected String getExtension() {
        return JAVA;
    }

    @Override
    public void build(ITreeOut out, ITableViewMetadata model) {
        buildClass(out, model);
    }

    @Override
    protected void buildXmlBody(XmlSourceBuffer out, ITableViewMetadata model) {
        throw new NotImplementedException();
    }

}
