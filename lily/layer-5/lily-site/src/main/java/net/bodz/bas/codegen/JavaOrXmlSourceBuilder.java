package net.bodz.bas.codegen;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public abstract class JavaOrXmlSourceBuilder<model_t>
        extends SourceBuilder<model_t> {

    protected abstract QualifiedName getClassName(model_t model);

    public synchronized void buildClass(ITreeOut out, model_t model) {
        QualifiedName name = getClassName(model);
        out.println("package " + name.packageName + ";");
        out.println();

        BCharOut body = new BCharOut();
        JavaSourceWriter buffer = new JavaSourceWriter(TreeOutImpl.from(body));
        buildClassBody(buffer, model);

        buffer.im.dump(out);
        out.println();

        out.print(body);
        out.flush();
    }

    protected abstract void buildClassBody(JavaSourceWriter out, model_t model);

    public void buildXml(ITreeOut out, model_t model) {
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        XmlSourceBuffer buf = new XmlSourceBuffer(out);
        buildXmlBody(buf, model);
        out.flush();
    }

    protected abstract void buildXmlBody(XmlSourceBuffer out, model_t model);

}
