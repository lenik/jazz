package net.bodz.bas.io.res;

import java.nio.charset.Charset;

public interface IStreamInputSourceWrapper {

    IStreamInputSource getInputSource();

    IStreamInputSource getInputSource(String charsetName);

    IStreamInputSource getInputSource(Charset charset);

}
