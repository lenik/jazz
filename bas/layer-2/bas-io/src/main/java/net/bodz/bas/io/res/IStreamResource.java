package net.bodz.bas.io.res;

public interface IStreamResource
        extends IStreamInputSource, IStreamOutputTarget {

    Long getLength();

    void addOpenResourceListener(IOpenResourceListener listener);

    void removeOpenResourceListener(IOpenResourceListener listener);

}
