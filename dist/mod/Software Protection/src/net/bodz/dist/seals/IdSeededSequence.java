package net.bodz.dist.seals;

import java.nio.ByteBuffer;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.dist.sysid.SysIdProvider;

public abstract class IdSeededSequence extends _Sequence {

    private final int seedById;
    private Sequence  randomizer;

    /**
     * @throws SystemException
     *             if exception happens when finding the seed.
     */
    public IdSeededSequence() throws SystemException {
        SysIdProvider idProvider = findIdProvider();
        if (idProvider == null)
            seedById = 0xDEADCAFE;
        else
            seedById = digest(idProvider.getId());
        randomizer = new RandomSequence(seedById);
    }

    protected int digest(byte[] b) {
        AccumEntropy entropy = new AccumEntropy(16, 157);
        ByteBuffer bb = ByteBuffer.wrap(b);
        entropy.drop(bb);
        return entropy.getInt();
    }

    protected abstract SysIdProvider findIdProvider();

    @Override
    public void reset() {
        randomizer.reset();
    }

    @Override
    public int next() {
        return randomizer.next();
    }

    @Override
    public void next(ByteBuffer buffer) {
        randomizer.next(buffer);
    }

}
