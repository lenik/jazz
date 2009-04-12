package net.bodz.bas.snm;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.bodz.bas.cli.util.ModulesRoot;
import net.bodz.bas.io.Files;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.text.util.Patterns;

public class SJEclipse {

    static PatternFilter linkFilter = new PatternFilter("*.link", true); //$NON-NLS-1$

    static class PatternFilter implements FilenameFilter {

        private final Pattern pattern;
        private final boolean not;

        public PatternFilter(Pattern pattern) {
            this(pattern, false);
        }

        public PatternFilter(Pattern pattern, boolean not) {
            if (pattern == null)
                throw new NullPointerException("null pattern"); //$NON-NLS-1$
            this.pattern = pattern;
            this.not = not;
        }

        public PatternFilter(String wildcards, boolean caseInsensitive,
                boolean not) {
            this(Patterns.fromWildcards(wildcards, caseInsensitive), not);
        }

        public PatternFilter(String wildcards, boolean caseInsensitive) {
            this(wildcards, caseInsensitive, false);
        }

        public PatternFilter(String wildcards) {
            this(wildcards, false, false);
        }

        @Override
        public boolean accept(File dir, String name) {
            boolean matched = pattern.matcher(name).matches();
            return matched ^ not;
        }

    }

    static ModulesRoot       abcd;
    static List<ModulesRoot> searches;
    static {
        searches = new ArrayList<ModulesRoot>();

        String lapiota = System.getenv("LAPIOTA"); //$NON-NLS-1$
        if (lapiota != null) {
            File d = new File(lapiota, "abc.d"); //$NON-NLS-1$
            abcd = new ModulesRoot(d);
            d = abcd.findexp("eclipse-*"); //$NON-NLS-1$
            if (d != null) {
                configEclipse(d);
            }
        }
    }

    public static void addSearch(File dir) {
        if (dir.isDirectory())
            searches.add(new ModulesRoot(dir));
    }

    static void configEclipse(File eclipsed) {
        if (!eclipsed.isDirectory())
            throw new IllegalArgumentException(AppNLS
                    .getString("SJEclipse.notDir") + eclipsed); //$NON-NLS-1$
        addEclipse(eclipsed);
    }

    static void addEclipse(File eclipsed) {
        addSearch(new File(eclipsed, "plugins")); //$NON-NLS-1$
        addSearch(new File(eclipsed, "dropins")); //$NON-NLS-1$
        parseLinks(new File(eclipsed, "dropins")); //$NON-NLS-1$
        parseLinks(new File(eclipsed, "links")); //$NON-NLS-1$
    }

    static void parseLinks(File linkDir) {
        assert linkDir != null;
        if (!linkDir.isDirectory())
            return;
        File base = linkDir.getParentFile();
        for (String link : linkDir.list(linkFilter)) {
            File linkFile = new File(linkDir, link);
            try {
                String s = Files.readLine(linkFile);
                assert s != null;
                int eq = s.indexOf('=');
                if (eq == -1)
                    continue;
                String key = s.substring(0, eq);
                if (!"path".equals(key)) //$NON-NLS-1$
                    continue;
                String val = s.substring(eq + 1).trim();
                File linkTarget = Files.canoniOf(base, val);
                if (linkTarget.isDirectory()) {
                    File d = linkTarget;
                    File d_eclipse = new File(d, "eclipse"); //$NON-NLS-1$
                    if (d_eclipse.isDirectory())
                        d = d_eclipse;
                    addEclipse(d);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @example org.eclipse.swt.win32.win32.x86_
     */
    public static URL findlib(String prefix, boolean errorFail) {
        String exp = prefix + "*"; //$NON-NLS-1$
        for (ModulesRoot mroot : searches) {
            File find = mroot.findexp(exp);
            if (find != null)
                return Files.getURL(find);
        }
        if (errorFail)
            throw new Error(AppNLS.getString("SJEclipse.cantFind") + prefix); //$NON-NLS-1$
        return null;
    }

    public static URL findlib(String prefix) {
        return findlib(prefix, false);
    }

}
