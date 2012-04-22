package net.bodz.art.obfuz.seals;

import net.bodz.art.obfuz.nls.ProtectNLS;
import net.bodz.art.obfuz.pm.ProtectionModel;
import net.bodz.art.obfuz.pm.ProtectionModels;

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
            throw new NullPointerException("text"); //$NON-NLS-1$
        String[] segs = text.split("-"); //$NON-NLS-1$
        if (segs.length < 1)
            throw new IllegalArgumentException(ProtectNLS.getString("Serial.noHeader")); //$NON-NLS-1$
        String head = segs[0];

        String modelName = ProtectionModels.floor(head);
        if (modelName == null)
            throw new IllegalArgumentException(ProtectNLS.getString("Serial.badModel") + head); //$NON-NLS-1$
        head = head.substring(modelName.length());
        setModelName(modelName);

        if (head.length() == 0)
            throw new IllegalArgumentException(ProtectNLS.getString("Serial.noProduct")); //$NON-NLS-1$
        int productLen = Integer.parseInt(head.substring(0, 1));
        head = head.substring(1);
        if (head.length() < productLen)
            throw new IllegalArgumentException(ProtectNLS.getString("Serial.invalidProductLen")); //$NON-NLS-1$

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
            throw new IllegalArgumentException(ProtectNLS.getString("Serial.badModel") + modelName); //$NON-NLS-1$
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
        String productLen = "" + product.length(); //$NON-NLS-1$
        String module = "" + this.module; //$NON-NLS-1$
        String head = modelName + productLen + product + module;
        if (sections == null)
            return head;
        StringBuffer buf = new StringBuffer();
        buf.append(head);
        for (int i = 0; i < sections.length; i++) {
            if (i != 0)
                buf.append("-"); //$NON-NLS-1$
            int sect = sections[i];
            buf.append(sect);
        }
        return buf.toString();
    }

}
