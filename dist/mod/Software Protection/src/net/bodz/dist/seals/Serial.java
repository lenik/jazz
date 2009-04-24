package net.bodz.dist.seals;

import net.bodz.dist.pm.ProtectionModel;
import net.bodz.dist.pm.ProtectionModels;

public class Serial {

    /**
     * model product-len product* module - section* </code>
     * <ul>
     * <li>model: ascii chars by max-len matching
     * <li>product-len: digit
     * <li>product*: ascii chars
     * <li>module: encoded int
     * <li>section: encoded int
     * </ul>
     * Examples:
     * <ul>
     * <li>
     * <code>A2QT1-Verification-Decryption-TrapDoor-Activation</code>
     * </ul>
     */
    private String          modelName;
    private ProtectionModel model;
    private String          product;
    private int             module;
    private int[]           sections;

    public Serial(String text) {
        if (text == null)
            throw new NullPointerException("text");
        String[] segs = text.split("-");
        if (segs.length < 1)
            throw new IllegalArgumentException("No header");
        String head = segs[0];

        String modelName = ProtectionModels.floor(head);
        if (modelName == null)
            throw new IllegalArgumentException("bad model: " + head);
        head = head.substring(modelName.length());
        setModelName(modelName);

        if (head.length() == 0)
            throw new IllegalArgumentException("no product specified");
        int productLen = Integer.parseInt(head.substring(0, 1));
        head = head.substring(1);
        if (head.length() < productLen)
            throw new IllegalArgumentException("invalid product length");

        product = head.substring(0, productLen);
        head = head.substring(productLen);

        if (head.length() > 0)
            module = CodeSet.decode(head);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        ProtectionModel model = ProtectionModels.get(modelName);
        if (model == null)
            throw new IllegalArgumentException("bad model: " + modelName);
        this.modelName = modelName;
        this.model = model;
    }

    public ProtectionModel getModel() {
        return model;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int[] getSections() {
        return sections;
    }

    public void setSections(int[] sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        String productLen = "" + product.length();
        String module = "" + this.module;
        String head = modelName + productLen + product + module;
        if (sections == null)
            return head;
        StringBuffer buf = new StringBuffer();
        buf.append(head);
        for (int i = 0; i < sections.length; i++) {
            if (i != 0)
                buf.append("-");
            int sect = sections[i];
            buf.append(sect);
        }
        return buf.toString();
    }

}
