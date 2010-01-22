package net.bodz.bas.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @test {@link FileFindTest}
 */
public class FileFind {

    /**
     * @return <code>null</code> if no match.
     */
    public static List<File> find(String wildPath) {
        List<File> found = new ArrayList<File>();
        find(wildPath, found);
        if (found.size() == 0)
            return null;
        return found;
    }

    static void find(String wildPath, Collection<File> found) {
        int ast = wildPath.indexOf('*');
        if (ast == -1) {
            File f = new File(wildPath);
            if (f.exists())
                found.add(f);
        } else {
            int estart = wildPath.lastIndexOf('/', ast);
            if (estart == -1)
                throw new IllegalArgumentException("invalid wild exp: " + wildPath);
            estart++;
            int eend = wildPath.indexOf('/', ast);
            if (eend == -1)
                eend = wildPath.length();
            String elm = wildPath.substring(estart, eend);
            String regex = elm;
            regex = regex.replace(".", "\\.");
            regex = regex.replace("*", ".*");
            Pattern p = Pattern.compile("^" + regex + "$");
            File parent = new File(wildPath.substring(0, estart));
            if (!parent.isDirectory()) {
                // maybe file or non-exist.
                // usually occured when multiple-* scan.
                return;
            }
            for (File f : parent.listFiles()) {
                String fname = f.getName();
                if (p.matcher(fname).matches()) {
                    String it = f.getPath() + wildPath.substring(eend);
                    find(it, found);
                }
            }
        }
    }

}
