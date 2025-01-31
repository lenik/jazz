package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;

public abstract class AbstractStreamOutputTarget<This>
        extends AbstractResourceEntry<This>
        implements IStreamOutputTarget {

    public AbstractStreamOutputTarget() {
    }

}
