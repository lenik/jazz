package net.bodz.bas.io;

import java.util.ArrayList;
import java.util.List;

public class SyncIndentedOuts
        extends DecoratedTreeOut {

    private static final long serialVersionUID = 1L;

    List<ITreeOut> outs = new ArrayList<>();

    public SyncIndentedOuts(ITreeOut defaultOut, ITreeOut... otherOuts) {
        super(defaultOut);
        outs.add(defaultOut);
        for (ITreeOut out : otherOuts)
            outs.add(out);
    }

    public ITreeOut getDefaultOut() {
        return getWrapped();
    }

    public void setDefaultOut(ITreeOut out) {
        _orig = out;
    }

    public ITreeOut get(int index) {
        return outs.get(index);
    }

    @Override
    public int enter() {
        int ret = -1;
        for (ITreeOut out : outs) {
            int n = out.enter();
            if (out == _orig)
                ret = n;
        }
        return ret;
    }

    @Override
    public int leave() {
        int ret = -1;
        for (ITreeOut out : outs) {
            int n = out.leave();
            if (out == _orig)
                ret = n;
        }
        return ret;
    }

}
