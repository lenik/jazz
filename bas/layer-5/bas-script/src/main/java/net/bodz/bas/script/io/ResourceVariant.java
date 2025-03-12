package net.bodz.bas.script.io;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ResourceVariant {

    static final Logger logger = LoggerFactory.getLogger(ResourceVariant.class);

    static final String ERR_NOT_SUPPORTED = "Not supported variant opeartion";

    public final ResourceVariantType type;
    File file;
    Path path;
    URL url;
    URI uri;
    char[] chars;
    byte[] bytes;
    int off;
    int len;

    public ResourceVariant(File file) {
        this.type = ResourceVariantType.FILE;
        this.file = file;
    }

    public ResourceVariant(Path path) {
        this.type = ResourceVariantType.PATH;
        this.path = path;
    }

    public ResourceVariant(URL url) {
        this.type = ResourceVariantType.URL;
        this.url = url;
    }

    public ResourceVariant(URI uri) {
        this.type = ResourceVariantType.URI;
        this.uri = uri;
    }

    public ResourceVariant(String text) {
        this(text.toCharArray());
    }

    public ResourceVariant(char[] chars) {
        this(chars, 0, chars.length);
    }

    public ResourceVariant(char[] chars, int off, int len) {
        this.type = ResourceVariantType.CHARS;
        this.chars = chars;
        this.off = off;
        this.len = len;
    }

    public ResourceVariant(byte[] bytes, int off, int len) {
        this.type = ResourceVariantType.BYTES;
        this.bytes = bytes;
        this.off = off;
        this.len = len;
    }

    public IStreamResource toResource() {
        switch (type) {
            case FILE:
                return ResFn.file(file);
            case PATH:
                return ResFn.path(path);
            case URL:
                return ResFn.url(url);
            case URI:
                try {
                    URL url = uri.toURL();
                    return ResFn.url(url);
                } catch (MalformedURLException e) {
                    throw new IllegalStateException(e.getMessage(), e);
                }
            case CHARS:
                return ResFn.chars(chars, off, len);
            case BYTES:
                return ResFn.bytes(bytes, off, len);
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    public File toFile() {
        switch (type) {
            case FILE:
                return file;
            case PATH:
                return path.toFile();
            case URL:
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    throw new VariantException(e.getMessage(), e);
                }
                // pass-thru
            case URI:
                return new File(uri);
            case CHARS:
            case BYTES:
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    public Path toPath() {
        switch (type) {
            case FILE:
                return file.toPath();
            case PATH:
                return path;
            case URL:
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    throw new VariantException(e.getMessage(), e);
                }
                // pass-thru
            case URI:
                Path path = Paths.get(uri);
                if (path == null)
                    logger.error("got null path from: " + this);
                return path;
            case CHARS:
            case BYTES:
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    public URL toURL() {
        switch (type) {
            case FILE:
                try {
                    return file.toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new VariantException(e.getMessage(), e);
                }
            case PATH:
                uri = path.toUri();
                // pass-thru
            case URI:
                try {
                    return uri.toURL();
                } catch (MalformedURLException e) {
                    throw new VariantException(e.getMessage(), e);
                }
            case URL:
                return url;
            case CHARS:
            case BYTES:
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    public URI toURI() {
        switch (type) {
            case FILE:
                return file.toURI();
            case PATH:
                return path.toUri();
            case URI:
                return uri;
            case URL:
                try {
                    return url.toURI();
                } catch (URISyntaxException e) {
                    throw new VariantException(e.getMessage(), e);
                }
            case CHARS:
            case BYTES:
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    public ResourceVariant resolve(String spec) {
        if (spec == null)
            throw new NullPointerException("spec");
        switch (type) {
            case FILE:
                File otherFile = new File(file, spec);
                return new ResourceVariant(otherFile);
            case PATH:
                Path otherPath = path.resolve(spec);
                return new ResourceVariant(otherPath);
            case URL:
                URL otherUrl;
                try {
                    otherUrl = url.toURI().resolve(spec).toURL();
                } catch (URISyntaxException | MalformedURLException e) {
                    throw new VariantException(e.getMessage(), e);
                }
                return new ResourceVariant(otherUrl);
            case URI:
                URI otherUri = uri.resolve(spec);
                return new ResourceVariant(otherUri);
            case CHARS:
            case BYTES:
            default:
                throw new VariantException(ERR_NOT_SUPPORTED);
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(100);
        buf.append(type);
        buf.append(": ");
        switch (type) {
            case FILE:
                buf.append(file);
                break;
            case PATH:
                buf.append(path);
                break;
            case URL:
                buf.append(url);
                break;
            case URI:
                buf.append(uri);
                break;
            case CHARS:
                buf.append(chars);
                break;
            case BYTES:
                buf.append(bytes);
                break;
        }
        return buf.toString();
    }

}
