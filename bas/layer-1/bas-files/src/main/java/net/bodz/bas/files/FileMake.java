package net.bodz.bas.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.closure.alt.Pred2;
import net.bodz.bas.exceptions.IllegalArgumentTypeException;

public class FileMake {

    /**
     * @param inputs
     *            each input may be:
     *            <ul>
     *            <li>File inputFile
     *            <li>String inputFileName
     *            <li>Predicate2(output, File[] inputs) rule
     *            </ul>
     */
    @SuppressWarnings("unchecked")
    public static boolean make(File output, Object... inputs) {
        assert output != null;
        output = FilePath.canoniOf(output);
        File outd = output.getParentFile();
        List<File> files = new ArrayList<File>();
        long outl = output.exists() ? output.lastModified() : 0l;
        long mostRecent = outl;
        boolean succeeded = true;
        for (Object input : inputs) {
            assert input != null;
            File inputf;
            if (input instanceof File)
                inputf = (File) input;
            else if (input instanceof String)
                inputf = FilePath.canoniOf(outd, (String) input);
            else if (input instanceof Pred2) {
                if (mostRecent > outl) {
                    File[] finputs = files.toArray(new File[0]);
                    Pred2<File, File[]> ruledef = (Pred2<File, File[]>) input;
                    boolean succ = ruledef.eval(output, finputs);
                    succeeded = succeeded && succ;
                }
                files.clear();
                continue;
            } else
                throw new IllegalArgumentTypeException(input);
            if (!inputf.exists())
                throw new IllegalArgumentException("input file isn\'t existed: " + inputf);
            files.add(inputf);
            if (inputf.lastModified() > mostRecent)
                mostRecent = inputf.lastModified();
        }
        return succeeded;
    }

}
