package net.bodz.dist.pro.seals;

import java.util.Random;

public class RandomSequence extends _Sequence {

    private final int seed;
    private Random    rand;

    public RandomSequence(int seed) {
        this.seed = seed;
        this.rand = new Random(seed);
    }

    @Override
    public void reset() {
        rand.setSeed(seed);
    }

    @Override
    public int next() {
        return rand.nextInt();
    }

}
