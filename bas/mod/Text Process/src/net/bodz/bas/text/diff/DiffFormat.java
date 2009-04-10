package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.types.Obtain;

@Obtain(registry = DiffFormats.class)
public interface DiffFormat {

    void format(List<?> a, List<?> b, List<DiffInfo> diffs, CharOut out);

}
