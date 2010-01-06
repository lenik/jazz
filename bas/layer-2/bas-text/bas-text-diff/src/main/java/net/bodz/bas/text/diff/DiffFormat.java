package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.aspect.annotation.Obtain;
import net.bodz.bas.io.out.CharOut;

@Obtain(registry = DiffFormats.class)
public interface DiffFormat {

    void format(List<?> a, List<?> b, List<DiffInfo> diffs, CharOut out);

}
