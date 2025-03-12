package net.bodz.bas.servlet.persist;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import net.bodz.bas.c.jakarta.servlet.DecoratedServletContext;
import net.bodz.bas.c.jakarta.servlet.http.DecoratedHttpSession;
import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.util.IAttributes;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.ResFn;

public class AppPersistor {

    RegistryScheme registry;
    AppFileFormat fmt;
    String encoding = "utf-8";

    public AppPersistor(RegistryScheme registry) {
        if (registry == null)
            throw new NullPointerException("registry");
        this.registry = registry;
        this.fmt = new AppFileFormat();
    }

    public void save(final ServletContext servletContext)
            throws IOException, FormatException {
        Path contextFile = registry.getContextFile();
        save(contextFile, new DecoratedServletContext(servletContext));
    }

    public void save(final HttpSession session)
            throws IOException, FormatException {
        String id = session.getId();
        Path sessionFile = registry.getSessionFile(id);
        save(sessionFile, new DecoratedHttpSession(session));
    }

    public void save(Path file, IAttributes attributes)
            throws IOException, FormatException {
        try (OutputStream out = Files.newOutputStream(file)) {
            fmt.saveAttributes(out, attributes);
        }
    }

    public void loadServletContext(ServletContext servletContext)
            throws IOException, ParseException {
        Path file = registry.getContextFile();
        if (FileFn.exists(file))
            load(file, new DecoratedServletContext(servletContext));
    }

    public void loadSession(HttpSession session)
            throws IOException, ParseException {
        Path file = registry.getSessionFile(session.getId());
        if (FileFn.exists(file))
            load(file, new DecoratedHttpSession(session));
    }

    void load(Path file, IAttributes attributes)
            throws IOException, ParseException {
        String json = ResFn.path(file).read().readString();
        fmt.loadAttributes(json, attributes);
    }

}
