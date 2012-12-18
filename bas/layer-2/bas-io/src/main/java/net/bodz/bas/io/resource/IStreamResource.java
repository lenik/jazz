package net.bodz.bas.io.resource;

public interface IStreamResource
        extends IStreamInputSource, IStreamOutputTarget {

    @Override
    IStreamResource clone();

    Long getLength();

    void addOpenResourceListener(IOpenResourceListener listener);

    void removeOpenResourceListener(IOpenResourceListener listener);

}
