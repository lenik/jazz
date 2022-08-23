package com.github.difflib.patch;

import java.util.List;

public class DeltaUtil {

    public static <T> void applyTo(AbstractDelta<T> delta, List<T> target)
            throws PatchFailedException {
        delta.applyTo(target);
    }

    public static <T> void restore(AbstractDelta<T> delta, List<T> target)
            throws PatchFailedException {
        delta.restore(target);
    }

}
