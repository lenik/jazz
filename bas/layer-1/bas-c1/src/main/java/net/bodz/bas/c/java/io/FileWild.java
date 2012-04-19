package net.bodz.bas.c.java.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class FileWild {

    /**
     * Find files matching the specified wildcards.
     * 
     * @return <code>null</code> if no match.
     */
    public static List<File> listFiles(String pathWithWildcards) {
        List<File> found = new ArrayList<File>();
        listFiles(pathWithWildcards, found);
        if (found.size() == 0)
            return null;
        return found;
    }

    public static void listFiles(String pathWithWildcards, Collection<File> matchedFiles) {
        int ast = pathWithWildcards.indexOf('*');
        if (ast == -1) {
            File f = new File(pathWithWildcards);
            if (f.exists())
                matchedFiles.add(f);
        } else {
            int estart = pathWithWildcards.lastIndexOf('/', ast);
            if (estart == -1)
                throw new IllegalArgumentException("invalid wild exp: " + pathWithWildcards);
            estart++;
            int eend = pathWithWildcards.indexOf('/', ast);
            if (eend == -1)
                eend = pathWithWildcards.length();
            String elm = pathWithWildcards.substring(estart, eend);
            String regex = elm;
            regex = regex.replace(".", "\\.");
            regex = regex.replace("*", ".*");
            Pattern p = Pattern.compile("^" + regex + "$");
            File parent = new File(pathWithWildcards.substring(0, estart));
            if (!parent.isDirectory()) {
                // maybe file or non-exist.
                // usually occured when multiple-* scan.
                return;
            }
            for (File f : parent.listFiles()) {
                String fname = f.getName();
                if (p.matcher(fname).matches()) {
                    String it = f.getPath() + pathWithWildcards.substring(eend);
                    listFiles(it, matchedFiles);
                }
            }
        }
    }

}
