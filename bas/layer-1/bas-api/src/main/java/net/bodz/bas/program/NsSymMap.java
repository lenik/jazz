package net.bodz.bas.program;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NsSymMap {

    ClassLoader loader;
    String resourceName;

    /**
     *
     * alias = [type] FQCN
     *
     * [ns] sym = TEXT
     *
     * Old Example: hello = main user.HelloProgram
     *
     * New Example: main hello = user.HelloProgram
     */
    Pattern oldSyntax = Pattern.compile("^\\s*(\\S+)\\s*=\\s*((\\w+)\\s+)?(\\S+)\\s*$");
    Pattern newSyntax = Pattern.compile("^\\s*((\\w+)\\s+)?(\\S+)\\s*=\\s*(\\S+)\\s*$");

    boolean loaded;
    Map<String, Map<String, String>> nsMap = new HashMap<>();
    public static final String NS_DEFAULT = "default";

    public NsSymMap(ClassLoader loader, String resourceName) {
        this.loader = loader;
        this.resourceName = resourceName;
    }

    public synchronized void load()
            throws IOException {
        if (loaded)
            return;
        loadDefFromResources();
        loaded = true;
    }

    void autoLoad() {
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Set<String> keySet() {
        autoLoad();
        return nsMap.keySet();
    }

    Map<String, String> forNs(String ns) {
        Map<String, String> syms = nsMap.get(ns);
        if (syms == null) {
            syms = new TreeMap<>();
            nsMap.put(ns, syms);
        }
        return syms;
    }

    public Map<String, String> getSymMap(String ns) {
        autoLoad();
        return nsMap.get(ns);
    }

    public String getText(String ns, String sym) {
        autoLoad();
        if (ns == null)
            throw new NullPointerException("ns");
        if (sym == null)
            throw new NullPointerException("sym");
        Map<String, String> syms = nsMap.get(ns);
        if (syms == null) {
            syms = nsMap.get(NS_DEFAULT);
            if (syms == null)
                return null;
        }
        String text = syms.get(sym);
        return text;
    }

    public Class<?> resolve(String ns, String sym, ClassLoader loader)
            throws ClassNotFoundException {
        autoLoad();
        String fqcn = getText(ns, sym);
        if (fqcn == null)
            return null;
        return Class.forName(fqcn, false, loader);
    }

    void loadDefFromResources()
            throws IOException {
        Enumeration<URL> resources = loader.getResources(resourceName);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            InputStream in = url.openStream();
            ByteArrayOutputStream buf = new ByteArrayOutputStream(1000);
            byte[] block = new byte[4096];
            int nb;
            while ((nb = in.read(block)) != -1)
                buf.write(block, 0, nb);
            in.close();

            String contents = new String(buf.toByteArray(), "utf-8");
            BufferedReader rdr = new BufferedReader(new StringReader(contents));
            String line;
            while ((line = rdr.readLine()) != null) {
                Matcher matcher;

                matcher = oldSyntax.matcher(line);
                if (matcher.find()) {
                    String sym = matcher.group(1);
                    String ns = matcher.group(3);
                    if (ns == null)
                        ns = NS_DEFAULT;
                    String text = matcher.group(4);
                    Map<String, String> syms = forNs(ns);
                    syms.put(sym, text);
                    continue;
                }

                matcher = newSyntax.matcher(line);
                if (matcher.find()) {
                    String ns = matcher.group(2);
                    if (ns == null)
                        ns = NS_DEFAULT;
                    String sym = matcher.group(3);
                    String text = matcher.group(4);
                    Map<String, String> syms = forNs(ns);
                    syms.put(sym, text);
                    continue;
                }

            }
        }
    }

    public void dump(Appendable out, String prefix)
            throws IOException {
        for (String ns : keySet()) {
            Map<String, String> syms = forNs(ns);
            for (String sym : syms.keySet()) {
                String fqcn = syms.get(sym);
                String line = String.format("%s[%s] %s: %s\n", prefix, ns, sym, fqcn);
                out.append(line);
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        NsSymMap map = new NsSymMap(ClassLoader.getSystemClassLoader(), ProgramLauncher.PROGRAMS_RESOURCE);
        map.dump(System.err, "");
    }

}
