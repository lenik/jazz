package net.bodz.product.seals;

import java.util.Random;

public class RandomSequence implements Sequence {

    final Random rand;

    private RandomSequence(int seed) {
        rand = new Random(seed);
    }

    @Override
    public int next() {
        return rand.nextInt();
    }

}
