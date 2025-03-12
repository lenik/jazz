package net.bodz.lily.entity.attachment;

public class AttScreenshot
        extends DefaultBackedFile {

    AttScreenpack pack;

    public AttScreenshot(AttScreenpack pack) {
        if (pack == null)
            throw new NullPointerException("pack");
        this.pack = pack;
    }

}
