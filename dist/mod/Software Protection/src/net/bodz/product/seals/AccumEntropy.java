package net.bodz.product.seals;

public class AccumEntropy extends _Entropy {

    private final int multiplier;
    private int       acc;

    public AccumEntropy() {
        this(97);
    }

    public AccumEntropy(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void drop(byte b) {
        acc = acc * multiplier + b;
    }

    @Override
    public int get() {
        return acc;
    }

}
