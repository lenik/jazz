package net.bodz.bas.io.resource;

import java.nio.charset.Charset;

public interface IStreamResourceWrapper
        extends IStreamInputSourceWrapper, IStreamOutputTargetWrapper {

    IStreamResource getResource();

    IStreamResource getResource(Charset charset);

    IStreamResource getResource(String charsetName);

}
