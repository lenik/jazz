package net.bodz.bas.db.ctx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.java.nio.file.PathFn;
import net.bodz.bas.c.java.util.stream.iterable.Stream;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.fs.IDirForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class LocalDataContextProvider
        implements
            IDataContextProvider,
            IDirForm {

    Path folder;

    Map<String, ConnectOptions> connectOptionsMap = new HashMap<>();
    Map<String, DataContext> contextCacheMap = new HashMap<>(); // new WeakHashMap<>();

    public LocalDataContextProvider(Path folder) {
        if (folder == null)
            throw new NullPointerException("folder");
        try {
            readObject(folder);
        } catch (Exception e) {
            throw new IllegalUsageException("error loading " + folder + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, ConnectOptions> getConnectOptionsMap() {
        return connectOptionsMap;
    }

    @Override
    public ConnectOptions getConnectOptions(String key) {
        return connectOptionsMap.get(key);
    }

    /**
     * @throws DuplicatedKeyException
     *             if <code>key</code> exists.
     */
    public synchronized void addConnectOptions(String key, ConnectOptions options) {
        ConnectOptions val = connectOptionsMap.get(key);
        if (val != null)
            throw new DuplicatedKeyException(key, val);
        connectOptionsMap.put(key, options);
    }

    @Override
    public synchronized DataContext getDataContext(String key) {
        DataContext dataContext = contextCacheMap.get(key);
        if (dataContext == null) {
            ConnectOptions options = getConnectOptions(key);
            if (options == null)
                return null;
            dataContext = new DataContext(options);
            contextCacheMap.put(key, dataContext);
        }
        return dataContext;
    }

    @Override
    public void readObject(Path folder)
            throws IOException, ParseException {
        readObject(folder, null);
        this.folder = folder;
    }

    public void readObject(Path folder, String folderPathInfo)
            throws IOException, ParseException {

        try (Stream<Path> stream = PathFn.list(folder)) {
            for (Path file : stream) {
                String name = file.getFileName().toString();
                String pathInfo = folderPathInfo;
                if (pathInfo == null || pathInfo.isEmpty())
                    pathInfo = name;
                else {
                    pathInfo += "/" + name;
                }

                if (Files.isRegularFile(file)) {
                    addConfigFile(file, pathInfo);
                } else if (Files.isDirectory(file)) {
                    readObject(file, pathInfo);
                } else {
                    throw new UnexpectedException("neither file nor folder: " + file);
                }
            }
        }
    }

    boolean addConfigFile(Path file, String pathInfo)
            throws ParseException {
        int lastDot = pathInfo.lastIndexOf('.');

        String key = pathInfo;
        String extension = "";
        if (lastDot != -1) {
            key = pathInfo.substring(0, lastDot);
            extension = pathInfo.substring(lastDot + 1);
        }

        ConnectOptions options = new ConnectOptions(pathInfo);

        switch (extension) {
            case "xml":
                options.setSourceUri(file.toUri().toString());
                try {
                    XmlFn.load(options, file);
                } catch (Exception e) {
                    throw new ParseException(e.getMessage(), e);
                }
                addConnectOptions(key, options);
                return true;

            case "json":
                options.setSourceUri(file.toUri().toString());
                try {
                    JsonFn.load(options, file, JsonFormOptions.PRETTY);
                } catch (Exception e) {
                    throw new ParseException(e.getMessage(), e);
                }
                addConnectOptions(key, options);
                return true;
        }
        return false;
    }

    @Override
    public void writeObject(Path folder)
            throws IOException, FormatException {
        for (String key : connectOptionsMap.keySet()) {
            ConnectOptions options = connectOptionsMap.get(key);

            String fileName = key + ".xml";
            Path xmlFile = folder.resolve(fileName);
            try {
                XmlFn.save(options, xmlFile);
            } catch (XMLStreamException e) {
                throw new FormatException(e.getMessage(), e);
            }
        }
    }

}
