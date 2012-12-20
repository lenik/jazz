package net.bodz.bas.c.java.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class FileGlob {

    /**
     * Find files matching the specified wildcards.
     * 
     * @return <code>null</code> if no match.
     */
    public static List<File> listFiles(String pathGlob) {
        List<File> found = new ArrayList<File>();
        listFiles(pathGlob, found);
        if (found.size() == 0)
            return null;
        return found;
    }

    public static void listFiles(String pathGlob, Collection<File> matchedFiles) {
        int ast = pathGlob.indexOf('*');
        if (ast == -1) {
            File f = new File(pathGlob);
            if (f.exists())
                matchedFiles.add(f);
        } else {
            int estart = pathGlob.lastIndexOf('/', ast);
            if (estart == -1)
                throw new IllegalArgumentException("invalid glob: " + pathGlob);
            estart++;
            int eend = pathGlob.indexOf('/', ast);
            if (eend == -1)
                eend = pathGlob.length();
            String elm = pathGlob.substring(estart, eend);
            String regex = elm;
            regex = regex.replace(".", "\\.");
            regex = regex.replace("*", ".*");
            Pattern p = Pattern.compile("^" + regex + "$");
            File parent = new File(pathGlob.substring(0, estart));
            if (!parent.isDirectory()) {
                // maybe file or non-exist.
                // usually occured when multiple-* scan.
                return;
            }
            for (File f : parent.listFiles()) {
                String fname = f.getName();
                if (p.matcher(fname).matches()) {
                    String it = f.getPath() + pathGlob.substring(eend);
                    listFiles(it, matchedFiles);
                }
            }
        }
    }

}
