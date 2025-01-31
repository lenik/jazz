package net.bodz.bas.io.res;

import net.bodz.bas.meta.decl.DefaultImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;

public interface IMutableResourceEntry<This>
        extends IResourceEntry {

    void setPath(String path);

    @SuppressWarnings("unchecked")
    default This path(String path) {
        setName(path);
        return (This) this;
    }

    default void setName(String name) {
        String path = getPath();
        int sep = path.lastIndexOf('.');
        if (sep == -1) {
            setPath(name);
        }
        else {
            String dir = path.substring(0, sep + 1);
            // String base = path.substring(sep + 1);
            setPath(dir + name);
        }
    }

    @SuppressWarnings("unchecked")
    default This name(String name) {
        setName(name);
        return (This) this;
    }

    void setCharset(Charset charset);

    @SuppressWarnings("unchecked")
    default This charset(Charset charset) {
        setCharset(charset);
        return (This) this;
    }

    default void setCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        Charset charset = Charset.forName(charsetName);
        setCharset(charset);
    }

    @SuppressWarnings("unchecked")
    default This charset(String charsetName) {
        setCharset(charsetName);
        return (This) this;
    }

    void setTextMode(boolean mode);

    @SuppressWarnings("unchecked")
    default This textMode(boolean mode) {
        setTextMode(mode);
        return (This) this;
    }

    @SuppressWarnings("unchecked")
    default This textMode() {
        setTextMode(true);
        return (This) this;
    }

    @SuppressWarnings("unchecked")
    default This binMode() {
        setTextMode(false);
        return (This) this;
    }

}
