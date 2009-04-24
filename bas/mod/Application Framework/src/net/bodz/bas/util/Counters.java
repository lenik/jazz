package net.bodz.bas.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import net.bodz.bas.a.Counts;
import net.bodz.bas.codec.TextCodec;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.snm.SJProject;
import net.bodz.bas.types.TreeTextMap;

/**
 * @test {@link CountersTest}
 */
public class Counters extends TreeTextMap<Counter> {

    private static final long serialVersionUID = 8717426735620703383L;

    private File              propertyFileToSave;
    private ResourceBundle    bundle;

    public Counters(Class<?> clazz) {
        this(clazz, null, false);
    }

    public Counters(Class<?> clazz, String resourceName, boolean srcSide) {
        if (clazz == null)
            throw new NullPointerException("clazz"); //$NON-NLS-1$
        parse(clazz, resourceName, srcSide);
    }

    public Counters(File propertyFile) throws IOException, ParseException {
        this.propertyFileToSave = propertyFile;
        Properties properties = Files.loadProperties(propertyFile, "utf-8"); //$NON-NLS-1$
        parse(properties);
    }

    static final String ATTR_PARENTS   = ".parents"; //$NON-NLS-1$
    static final String ATTR_CODEC     = ".codec"; //$NON-NLS-1$
    static final String ATTR_FORMAT    = ".format"; //$NON-NLS-1$
    static final String ATTR_PRECODED  = ".precoded"; //$NON-NLS-1$
    static final String ATTR_INIT      = ".init"; //$NON-NLS-1$
    static final String ATTR_INCR      = ".incr"; //$NON-NLS-1$
    static final String ATTR_TIMESTAMP = ".t"; //$NON-NLS-1$

    class Refs extends AbstractSet<Counter> {

        Set<String> refs;

        public Refs() {
            refs = new HashSet<String>();
        }

        public boolean addRef(String name) {
            return refs.add(name);
        }

        public boolean removeRef(String name) {
            return refs.remove(name);
        }

        @Override
        public Iterator<Counter> iterator() {
            final Iterator<String> iter = refs.iterator();
            return new Iterator<Counter>() {

                @Override
                public boolean hasNext() {
                    return iter.hasNext();
                }

                @Override
                public Counter next() {
                    String ref = iter.next();
                    return Counters.this.get(ref);
                }

                @Override
                public void remove() {
                    iter.remove();
                }

            };
        }

        @Override
        public int size() {
            return refs.size();
        }

    }

    void parse(Class<?> clazz, String resourceName, boolean srcSide) {
        // ClassDiag...
        if (clazz == null)
            throw new NullPointerException("clazz"); //$NON-NLS-1$
        if (resourceName == null) {
            Counts a = clazz.getAnnotation(Counts.class);
            resourceName = a.value();
            if (resourceName == null)
                throw new IllegalUsageException(AppNLS.getString("Counters.noCounterInfo") + clazz); //$NON-NLS-1$
        }

        URL url;
        if (srcSide) {
            url = SJProject.getSrcURLWithName(clazz, resourceName);
        } else
            url = clazz.getResource(resourceName);
        if ("file".equals(url.getProtocol())) //$NON-NLS-1$
            try {
                propertyFileToSave = Files.getFile(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        try {
            Properties properties = Files.loadProperties(url, "utf-8"); //$NON-NLS-1$
            parse(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // find and load NLS.
        try {
            bundle = ResourceBundle.getBundle(clazz.getName());
        } catch (MissingResourceException e) {
            // just ignore.
        }
    }

    void parse(Properties properties) throws ParseException {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = String.valueOf(entry.getKey());
            int dot = key.indexOf('.');
            if (dot != -1)
                key = key.substring(0, dot);
            if (containsKey(key)) // already parsed counter
                continue;

            String name = getDisplayName(key);
            Counter counter = new Counter(name);
            put(key, counter);

            String parentNames = properties.getProperty(key + ATTR_PARENTS);
            Refs parents = new Refs();
            if (parentNames != null)
                for (String parentName : parentNames.split(",")) //$NON-NLS-1$
                    parents.addRef(parentName.trim());
            counter.setParents(parents);

            String timestamp = properties.getProperty(ATTR_TIMESTAMP);
            if (timestamp != null)
                counter.setTimestamp(Long.parseLong(timestamp));

            String format = properties.getProperty(key + ATTR_FORMAT);
            if (format != null)
                counter.setCodec(new CounterFormat(format));
            else {
                String codecdef = properties.getProperty(key + ATTR_CODEC);
                if (codecdef != null)
                    counter.setCodec(parseCodec(codecdef));
            }

            boolean precoded = "1".equals(properties.getProperty(key + ATTR_PRECODED)); //$NON-NLS-1$
            counter.setPrecoded(precoded);

            String value = properties.getProperty(key);
            String init = properties.getProperty(key + ATTR_INIT);
            String incr = properties.getProperty(key + ATTR_INCR);
            try {
                if (precoded) {
                    TextCodec<Long> codec = counter.getCodec();
                    if (value != null)
                        counter.set(codec.decode(value));
                    if (init != null)
                        counter.setInit(codec.decode(init));
                    if (incr != null)
                        counter.setIncrement(codec.decode(incr));
                } else {
                    if (value != null)
                        counter.set(Long.parseLong(value));
                    if (init != null)
                        counter.setInit(Long.parseLong(init));
                    if (incr != null)
                        counter.setIncrement(Long.parseLong(incr));
                }
                if (value == null)
                    counter.init();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    void save(Properties properties) throws ParseException {
        for (Map.Entry<String, Counter> entry : entrySet()) {
            String name = entry.getKey();
            Counter counter = entry.getValue();
            boolean precoded = counter.isPrecoded();
            String value = precoded ? counter.format() : String.valueOf(counter.get());
            properties.setProperty(name, value);
            properties.setProperty(name + ATTR_TIMESTAMP, //
                    String.valueOf(counter.getTimestamp()));
        }
    }

    public void save() throws IOException {
        if (propertyFileToSave == null)
            throw new IOException(AppNLS.getString("Counters.noPropertyFile")); //$NON-NLS-1$
        Properties properties = Files.loadProperties(propertyFileToSave, "utf-8"); //$NON-NLS-1$
        try {
            save(properties);
        } catch (ParseException e) {
            throw new IOException(e);
        }
        FileOutputStream out = new FileOutputStream(propertyFileToSave);
        properties.store(out, AppNLS.getString("Counters.autoGenerated")); //$NON-NLS-1$
        out.close();
    }

    @SuppressWarnings("unchecked")
    public static TextCodec<Long> parseCodec(String s) throws ParseException {
        if (s == null)
            return Counter.defaultCodec;
        int colon = s.indexOf(':');
        String name = colon == -1 ? s : s.substring(0, colon);
        try {
            if (colon == -1)
                return (TextCodec<Long>) Class.forName(name).newInstance();
            else
                return (TextCodec<Long>) Class.forName(name).getConstructor(String.class)
                        .newInstance(s.substring(colon));
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public String getDisplayName(String name) {
        if (bundle != null)
            try {
                return bundle.getString(name);
            } catch (MissingResourceException e) {
            }
        return getDefaultDisplayName(name);
    }

    static ResourceBundle builtin;
    static {
        builtin = ResourceBundle.getBundle(Counters.class.getName());
    }

    static String getDefaultDisplayName(String name) {
        try {
            return builtin.getString(name);
        } catch (MissingResourceException e) {
            return name;
        }
    }

}
