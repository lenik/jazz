package net.bodz.bas.servlet.persist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.c.jakarta.servlet.DecoratedServletContext;
import net.bodz.bas.c.jakarta.servlet.http.DecoratedHttpSession;
import net.bodz.bas.c.util.IAttributes;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.ResFn;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

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
        File contextFile = registry.getContextFile();
        save(contextFile, new DecoratedServletContext(servletContext));
    }

    public void save(final HttpSession session)
            throws IOException, FormatException {
        String id = session.getId();
        File sessionFile = registry.getSessionFile(id);
        save(sessionFile, new DecoratedHttpSession(session));
    }

    public void save(File file, IAttributes attributes)
            throws IOException, FormatException {
        OutputStream out = new FileOutputStream(file);
        try {
            fmt.saveAttributes(out, attributes);
        } finally {
            out.close();
        }
    }

    public void loadServletContext(ServletContext servletContext)
            throws IOException, ParseException {
        File file = registry.getContextFile();
        if (file.exists())
            load(file, new DecoratedServletContext(servletContext));
    }

    public void loadSession(HttpSession session)
            throws IOException, ParseException {
        File file = registry.getSessionFile(session.getId());
        if (file.exists())
            load(file, new DecoratedHttpSession(session));
    }

    void load(File file, IAttributes attributes)
            throws IOException, ParseException {
        String json = ResFn.file(file).read().readString();
        fmt.loadAttributes(json, attributes);
    }

}
