package net.bodz.violet.art.impl;

import org.junit.Ignore;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Packaging;
import net.bodz.violet.art.PackagingSamples;

@Ignore
public class PackagingMapperTest
        extends AbstractMapperTest<Packaging, PackagingMask, PackagingMapper> {

    @Override
    public Packaging buildSample() {
        return PackagingSamples.build();
    }

}
