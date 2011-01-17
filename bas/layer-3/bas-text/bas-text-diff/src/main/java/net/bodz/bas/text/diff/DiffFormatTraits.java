package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IInstanceStore;

/**
 * @test {@link DiffFormatTraitsTest}
 */
public class DiffFormatTraits
        extends AbstractCommonTraits<DiffFormat> {

    public DiffFormatTraits() {
        super(DiffFormat.class);
        addStaticInstances(DiffFormats.class);
    }

    @Override
    public IInstanceStore<DiffFormat> getInstanceStore() {
        return this;
    }

}
