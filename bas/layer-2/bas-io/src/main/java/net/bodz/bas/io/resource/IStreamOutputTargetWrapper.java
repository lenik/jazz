package net.bodz.bas.io.resource;

import java.nio.charset.Charset;

public interface IStreamOutputTargetWrapper {

    IStreamOutputTarget getOutputTarget();

    IStreamOutputTarget getOutputTarget(String charsetName);

    IStreamOutputTarget getOutputTarget(Charset charset);

    IStreamOutputTarget getOutputTarget(boolean appendMode);

    IStreamOutputTarget getOutputTarget(boolean appendMode, String charsetName);

    IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset);

}
