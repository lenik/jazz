package net.bodz.bas.io.resource;

import java.nio.charset.Charset;

public interface IStreamInputSourceWrapper {

    IStreamInputSource getInputSource();

    IStreamInputSource getInputSource(String charsetName);

    IStreamInputSource getInputSource(Charset charset);

}
