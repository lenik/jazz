package net.bodz.bas.db.ctx;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.fs.IFilesForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class LocalDataHub
        extends DataHub
        implements
            IFilesForm {

    File folder;
    boolean loaded;

    Map<String, ConnectOptions> connectOptionsMap = new HashMap<>();
    Map<String, DataContext> contextCacheMap = new HashMap<>(); // new WeakHashMap<>();

    public LocalDataHub(File folder) {
        if (folder == null)
            throw new NullPointerException("folder");
        this.folder = folder;
    }

    public synchronized final void reload() {
        loaded = false;
        _loadImpl();
        loaded = true;
    }

    protected final void auoLoad() {
        if (!loaded) {
            synchronized (this) {
                if (!loaded) {
                    _loadImpl();
                    loaded = true;
                }
            }
        }
    }

    /**
     * @throws LoadException
     */
    protected void _loadImpl() {
        try {
            readObject(folder);
        } catch (Exception e) {
            throw new LoadException(e.getMessage(), e);
        }
    }

    @Override
    public ConnectOptions getConnectOptions(String key) {
        auoLoad();
        return connectOptionsMap.get(key);
    }

    @Override
    public ConnectOptions requireConnectOptions(String key) {
        auoLoad();
        ConnectOptions options = connectOptionsMap.get(key);
        if (options == null)
            throw new NoSuchKeyException(key);
        return options;
    }

    /**
     * @throws DuplicatedKeyException
     *             if <code>key</code> exists.
     */
    public synchronized void addConnectOptions(String key, ConnectOptions options) {
        auoLoad();
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
    public synchronized DataContext requireDataContext(String key) {
        DataContext dataContext = contextCacheMap.get(key);
        if (dataContext == null) {
            ConnectOptions options = requireConnectOptions(key);
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

        for (String fileName : folder.list()) {

            if (fileName.endsWith(".xml")) {
                File xmlFile = new File(folder, fileName);
                ConnectOptions options = new ConnectOptions();
                try {
                    XmlFn.load(options, xmlFile);
                } catch (LoaderException e) {
                    throw new ParseException(e.getMessage(), e);
                }
                String name = fileName.substring(0, fileName.length() - 4);
                addConnectOptions(name, options);
                continue;
            }

            if (fileName.endsWith(".json")) {
                File jsonFile = new File(folder, fileName);
                ConnectOptions options = new ConnectOptions();

                JsonFn.load(options, jsonFile);

                String name = fileName.substring(0, fileName.length() - 4);
                addConnectOptions(name, options);
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
