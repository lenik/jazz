package net.bodz.bas.crypto.trans;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.crypto.trans.fn.ICodeBin;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fn.TransformerArray;

public abstract class AbstractFlyingTransient
        implements
            IFlyingTransient {

    long window;

    public AbstractFlyingTransient(long window) {
        this.window = window;
    }

    @Override
    public long getWindow() {
        return window;
    }

    @Override
    public final String snapshot() {
        ICodeBin bin = getCodeAtTime(System.currentTimeMillis());
        return bin.toString();
    }

    @Override
    public final ICodeBin getCodeAtTime(long time) {
        long index = time / window;
        return getCode(index);
    }

    @Override
    public abstract ICodeBin getCode(long index);

    @Override
    public FlyingIndex lastIndexOf(ICodeBin code, int distance, int allowAhead) {
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, code, distance, allowAhead);
    }

    @Override
    public FlyingIndex lastIndexOf(long fromIndex, ICodeBin code, int distance, int allowAhead) {
        long pos = fromIndex + allowAhead;
        distance += allowAhead;
        for (int i = 0; i < distance; i++) {
            ICodeBin c = getCode(pos);
            if (code.equals(c))
                return new FlyingIndex(pos, window);
            pos--;
        }
        return FlyingIndex.NULL;
    }

    @Override
    public FlyingIndex lastIndexOf(String codeStr, int distance, int allowAhead) {
        long fromIndex = System.currentTimeMillis() / window;
        return lastIndexOf(fromIndex, codeStr, distance, allowAhead);
    }

    @Override
    public FlyingIndex lastIndexOf(long fromIndex, String codeStr, int distance, int allowAhead) {
        long pos = fromIndex + allowAhead;
        distance += allowAhead;
        for (int i = 0; i < distance; i++) {
            String c = getCode(pos).toString();
            if (codeStr.equals(c))
                return new FlyingIndex(pos, window);
            pos--;
        }
        return FlyingIndex.NULL;
    }

    @Override
    public IFlyingTransient getCore() {
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public IFlyingTransient transform(Function<? extends ICodeBin, ? extends ICodeBin> transformer) {
        return new TransformedFlyingTransient(this, transformer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IFlyingTransient transform(Function<? extends ICodeBin, ? extends ICodeBin>... transformers) {
        List<Function<ICodeBin, ICodeBin>> list = new ArrayList<Function<ICodeBin, ICodeBin>>();
        for (Function<? extends ICodeBin, ? extends ICodeBin> item : transformers) {
            Function<ICodeBin, ICodeBin> cast = (Function<ICodeBin, ICodeBin>) item;
            list.add(cast);
        }
        TransformerArray<ICodeBin> array = new TransformerArray<ICodeBin>(list);
        return transform(array);
    }

    @Override
    public void diag(String search, int distance, int allowAhead) {
        OutputStreamWriter osw = new OutputStreamWriter(System.err);
        IJsonOut out = new JsonWriter(osw);
        out.object();
        {
            out.entry("searchKey", search);

            long current = System.currentTimeMillis() / window;
            out.entry("window", window);
            out.entry("currentIndex", current);
            FlyingIndex lastIndex = lastIndexOf(search, distance, allowAhead);
            boolean got = lastIndex.exists();
            out.entry("searchExists", got);
            out.entry("absoluteLastIndex", lastIndex.getIndex());
            out.entry("relativeLastIndex", lastIndex.getRelativeIndex());
            out.key("tests");
            out.object();
            for (int i = allowAhead; i >= -distance; i--) {
                String nam = "code" + (i >= 0 ? "+" : "") + i;
                Object codeBin = getCode(current + i);
                out.entry(nam, codeBin.toString());
            }
            out.endObject();
        }
        out.endObject();
        try {
            osw.write('\n');
            osw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
