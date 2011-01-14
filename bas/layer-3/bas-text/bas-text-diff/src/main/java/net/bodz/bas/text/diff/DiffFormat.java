package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.meta.lang.TraitsClass;
import net.bodz.bas.sio.IPrintOut;

@TraitsClass(DiffFormatTraits.class)
public interface DiffFormat {

    void format(List<?> a, List<?> b, List<DiffInfo> diffs, IPrintOut out);

}
