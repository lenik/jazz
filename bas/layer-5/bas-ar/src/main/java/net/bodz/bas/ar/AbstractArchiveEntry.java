package net.bodz.bas.ar;

import java.nio.charset.Charset;

import net.bodz.bas.io.res.IStreamInputSource;

public abstract class AbstractArchiveEntry
        implements IArchiveEntry {

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public long getCreatedTime() {
        return getModifiedTime();
    }

    @Override
    public int getMode() {
        return defaultMode;
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public Integer getOwnerId() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public Integer getGroupId() {
        return null;
    }

    /** ⇱ Implementation Of {@link IStreamInputSourceWrapper}. */
    ;

    private static final Charset utf8Charset = Charset.forName("utf-8");

    protected Charset getPreferredContentCharset() {
        return utf8Charset;
    }

    @Override
    public final IStreamInputSource getInputSource() {
        Charset preferredContentCharset = getPreferredContentCharset();
        return getInputSource(preferredContentCharset);
    }

    @Override
    public final IStreamInputSource getInputSource(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getInputSource(charset);
    }

}
