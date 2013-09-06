package net.bodz.bas.io.res;

import java.nio.charset.Charset;

public interface IStreamOutputTargetWrapper {

    IStreamOutputTarget getOutputTarget();

    IStreamOutputTarget getOutputTarget(String charsetName);

    IStreamOutputTarget getOutputTarget(Charset charset);

}
