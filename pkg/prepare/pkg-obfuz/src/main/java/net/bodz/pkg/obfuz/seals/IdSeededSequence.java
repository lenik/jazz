package net.bodz.pkg.obfuz.seals;

import java.nio.ByteBuffer;

import net.bodz.bas.err.SystemException;
import net.bodz.pkg.obfuz.sysid.ISysIdProvider;

public abstract class IdSeededSequence
        extends _Sequence {

    private final int seedById;
    private ISequence randomizer;

    /**
     * @throws SystemException
     *             if exception happens when finding the seed.
     */
    public IdSeededSequence(int seed)
            throws SystemException {
        ISysIdProvider idProvider = findIdProvider();
        if (idProvider == null)
            seedById = seed + 0xDEADCAFE;
        else
            seedById = seed + digest(idProvider.getId());
        randomizer = new RandomSequence(seedById);
    }

    protected int digest(byte[] b) {
        AccumEntropy entropy = new AccumEntropy(16, 157);
        ByteBuffer bb = ByteBuffer.wrap(b);
        entropy.drop(bb);
        return entropy.getInt();
    }

    protected abstract ISysIdProvider findIdProvider();

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
