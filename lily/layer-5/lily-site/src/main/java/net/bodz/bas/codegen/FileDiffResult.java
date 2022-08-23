package net.bodz.bas.codegen;

import java.util.List;

import com.github.difflib.patch.Patch;

public class FileDiffResult {

    public List<String> v0;
    public List<String> v1;
    public Patch<String> patch;

    public FileDiffResult(List<String> v0, List<String> v1, Patch<String> patch) {
        this.v0 = v0;
        this.v1 = v1;
        this.patch = patch;
    }

    public boolean isSame() {
        return patch.getDeltas().isEmpty();
    }

    public boolean isDifferent() {
        return !isSame();
    }

}
