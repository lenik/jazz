package net.bodz.product.seals;

import java.util.Random;

public class RandomSequence implements Sequence {

    private final Random rand;

    public RandomSequence(int seed) {
        rand = new Random(seed);
    }

    @Override
    public int next() {
        return rand.nextInt();
    }

}
