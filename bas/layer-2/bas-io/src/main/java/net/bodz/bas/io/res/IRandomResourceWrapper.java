package net.bodz.bas.io.res;

import java.nio.charset.Charset;

public interface IRandomResourceWrapper
        extends IStreamResourceWrapper {

    IRandomResource getResource();

    IRandomResource getResource(Charset charset);

    IRandomResource getResource(String charsetName);

}
