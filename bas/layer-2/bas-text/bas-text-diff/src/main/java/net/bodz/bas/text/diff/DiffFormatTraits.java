package net.bodz.bas.text.diff;

import net.bodz.bas.type.AbstractTypeTraits;

/**
 * @test {@link DiffFormatTraitsTest}
 */
public class DiffFormatTraits
        extends AbstractTypeTraits<DiffFormat> {

    public DiffFormatTraits() {
        super(DiffFormat.class);
        addStaticInstances(DiffFormats.class);
    }

}
