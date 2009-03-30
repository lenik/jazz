package net.bodz.product.seals;

public class Serial {

    /**
     * product-len product* acc seq module - sections* </code>
     * <ul>
     * <li>product-len: digit
     * <li>product*: ascii chars
     * <li>acc: char
     * <li>seq: char
     * <li>module: encoded int
     * <li>section: encoded int
     * </ul>
     */
    private String   product;
    private int      module;
    private int[]    sections;

    private Sequence sequence;
    private Entropy  entropy;

    public Serial(String text) {
        if (text == null)
            throw new NullPointerException("text");
        String[] segs = text.split("-");
        if (segs.length < 1)
            throw new IllegalArgumentException("Too few segments: "
                    + segs.length);
        String head = segs[0];
        if (head.length() < 5)
            throw new IllegalArgumentException("Invalid header: " + head);
        int productLen = Integer.parseInt(head.substring(0, 1));
        product = head.substring(0, productLen);
        head = head.substring(1 + productLen);
        char ent = head.charAt(0);
        char seq = head.charAt(1);
        head = head.substring(2);
        module = CodeSet.decode(head);
        switch (ent) {
        case 'A':
            entropy = new AccumEntropy();
            break;
        default:
            throw new IllegalArgumentException("Bad entropy type: " + ent);
        }
        switch (seq) {
        case 'R':
            sequence = new RandomSequence(0);
            break;
        default:
            throw new IllegalArgumentException("Bad sequence type: " + seq);
        }
    }

    public Entropy getEntropy() {
        return entropy;
    }

    public Sequence getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return "";
    }

}
