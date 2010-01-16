package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.io.out.CharOut;
import net.bodz.bas.type.TypeTraitsBy;

@TypeTraitsBy(DiffFormatTraits.class)
public interface DiffFormat {

    void format(List<?> a, List<?> b, List<DiffInfo> diffs, CharOut out);

}
