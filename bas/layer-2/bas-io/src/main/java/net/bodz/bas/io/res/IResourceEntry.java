package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.LinkOption;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.sugar.IToChain;
import net.bodz.bas.sugar.Tooling;

public interface IResourceEntry
        extends IToChain {

    boolean isPathPresent();

    default boolean isNamePresent() {
        return isPathPresent();
    }

    @NotNull
    String getPath();

    @NotNull
    default String getName() {
        String path = getPath();
        int sep = path.lastIndexOf('/');
        return sep == -1 ? path : path.substring(sep + 1);
    }

    default boolean isTextModePreferred() {
        return false;
    }

    default boolean isTextMode() {
        return isTextModePreferred();
    }

    /**
     * @return Preferred non-<code>null</code> {@link Charset}, the default implementation returns
     * UTF-8 charset.
     */
    @NotNull
    default Charset getPreferredCharset() {
        return Charsets.UTF_8;
    }

    default Charset getCharset() {
        return getPreferredCharset();
    }

    /**
     * @return null if unknown.
     */
    default Long getLength()
            throws IOException {
        return null;
    }

    /**
     * @return null if unknown.
     */
    default OffsetDateTime getCreationDate() {
        return null;
    }

    /**
     * @return null if unknown.
     */
    default OffsetDateTime getLastModified() {
        return null;
    }

    default boolean exists() {
        return true;
    }

    default boolean canRead() {
        return true;
    }

    default boolean canWrite() {
        return true;
    }

    default boolean isDirectory(LinkOption... options) {
        return false;
    }

    default <T> T to(Class<T> toolClass) {
        return new Tooling(this).to(toolClass);
    }

}
