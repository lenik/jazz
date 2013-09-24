package net.bodz.bas.ar.zip;

public class ExtraFieldMetadata {

    public Class<? extends ExtraField> type;
    public boolean bigEndian;
    public boolean sizeTotal;

    public ExtraField newInstance() {
        try {
            return type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
