package net.bodz.geom.transform;

public class ChainedTransformer2f extends AbstractTransformer2f {

    private Transformer2f prefix;
    private Transformer2f suffix;
    private Transformer2f main;

    public ChainedTransformer2f(Transformer2f main) {
        this.main = main;
    }

    public ChainedTransformer2f() {
        this.main = null;
    }

    @Override
    public void mul(Transformer2f suffix) {
        // suffix = suffix.clone();
        if (this.suffix == null)
            this.suffix = suffix;
        else
            this.suffix.mul(suffix);
    }

    @Override
    public void mulBy(Transformer2f prefix) {
        // prefix = prefix.clone();
        if (this.prefix == null)
            this.prefix = prefix;
        else
            this.prefix.mulBy(prefix);
    }

    @Override
    public void invert() {
        /*
         * Invert the delegated Transformers and change the order from prefix -
         * main - suffix to Inv(suffix) - Inv(main) - Inv(prefix)
         */
        prefix.invert();
        main.invert();
        suffix.invert();
        Transformer2f t = prefix;
        prefix = suffix;
        suffix = t;
    }

    @Override
    public void transform(javax.vecmath.Vector2f vector) {
        if (prefix != null)
            prefix.transform(vector);
        if (main != null)
            main.transform(vector);
        if (suffix != null)
            suffix.transform(vector);
    }

    @Override
    public void invert(javax.vecmath.Vector2f vector) {
        if (prefix != null)
            prefix.invert(vector);
        if (main != null)
            main.invert(vector);
        if (suffix != null)
            suffix.invert(vector);
    }
}
