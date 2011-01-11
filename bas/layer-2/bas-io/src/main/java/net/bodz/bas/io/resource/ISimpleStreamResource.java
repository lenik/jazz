package net.bodz.bas.io.resource;

public interface ISimpleStreamResource
        extends ISimpleStreamInputSource, ISimpleStreamOutputTarget {

    @Override
    ISimpleStreamResource clone();

}
