package net.bodz.bas.db.ctx;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.fs.IFilesForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.tuple.Split;

@ExcludedFromIndex
public class LocalDataContextProvider
        implements
            IDataContextProvider,
            IFilesForm {

    File folder;

    Map<String, ConnectOptions> connectOptionsMap = new HashMap<>();
    Map<String, DataContext> contextCacheMap = new HashMap<>(); // new WeakHashMap<>();

    public LocalDataContextProvider(File folder) {
        if (folder == null)
            throw new NullPointerException("folder");
        try {
            readObject(folder);
        } catch (Exception e) {
            throw new IllegalUsageException("error loading " + folder + ": " + e.getMessage(), e);
        }
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
            throw new DuplicatedKeyException("already existed: " + key);
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
    public void readObject(File folder)
            throws IOException, ParseException {
        readObject(folder, "");
        this.folder = folder;
    }

    public void readObject(File folder, String prefix)
            throws IOException, ParseException {

        for (File file : folder.listFiles()) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                readObject(file, prefix + fileName + "/");
                continue;
            }

            Split nameExtension = Split.nameExtension(fileName);
            String name = nameExtension.a;
            String extension = nameExtension.b;
            if (extension == null)
                continue;

            String key = prefix + name;
            ConnectOptions options = new ConnectOptions();

            switch (nameExtension.b) {
            case "xml":
                options.setSourceUri(file.toURI().toString());
                try {
                    XmlFn.load(options, file);
                } catch (Exception e) {
                    throw new ParseException(e.getMessage(), e);
                }
                addConnectOptions(key, options);
                continue;

            case "json":
                options.setSourceUri(file.toURI().toString());
                try {
                    JsonFn.load(options, file, JsonFormOptions.PRETTY);
                } catch (Exception e) {
                    throw new ParseException(e.getMessage(), e);
                }
                addConnectOptions(key, options);
                continue;
            }

            // ignore unknown file.
        }
    }

    @Override
    public void writeObject(File folder)
            throws IOException, FormatException {
        for (String key : connectOptionsMap.keySet()) {
            ConnectOptions options = connectOptionsMap.get(key);

            String fileName = key + ".xml";
            File xmlFile = new File(folder, fileName);
            try {
                XmlFn.save(options, xmlFile);
            } catch (XMLStreamException e) {
                throw new FormatException(e.getMessage(), e);
            }
        }
    }

}
