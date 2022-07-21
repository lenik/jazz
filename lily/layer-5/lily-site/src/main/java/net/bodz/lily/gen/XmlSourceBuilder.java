package net.bodz.lily.gen;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public abstract class XmlSourceBuilder<model_t> {

    protected ITreeOut out;

    public String build(model_t model) {
        BCharOut buf = new BCharOut();
        ITreeOut root = TreeOutImpl.from(buf);
        build(root, model);
        return buf.toString();
    }

    public synchronized void build(ITreeOut out, model_t model) {
        this.out = out;
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        buildBody(model);
        out.flush();
    }

    protected abstract void buildBody(model_t model);

}
