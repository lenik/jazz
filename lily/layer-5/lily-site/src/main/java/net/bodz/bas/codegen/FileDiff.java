package net.bodz.bas.codegen;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.io.res.builtin.FileResource;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;

public class FileDiff {

    public static FileDiffResult diff(List<String> v0, File file1)
            throws IOException {
        List<String> v1;
        if (file1.exists())
            v1 = new FileResource(file1).read().readLines();
        else
            v1 = Collections.emptyList();

        Patch<String> patch = DiffUtils.diff(v0, v1);
        return new FileDiffResult(v0, v1, patch);
    }

    public static FileDiffResult diff(File file0, File file1)
            throws IOException {
        List<String> v0;
        if (file0.exists())
            v0 = new FileResource(file0).read().readLines();
        else
            v0 = Collections.emptyList();
        return diff(v0, file1);
    }

}
