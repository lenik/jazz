package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;

/**
 * @test {@link DiffFormatTraitsTest}
 */
public class DiffFormatTraits
        extends AbstractCommonTraits<DiffFormat> {

    public DiffFormatTraits() {
        super(DiffFormat.class);
        addStaticInstances(DiffFormats.class);
    }

}
