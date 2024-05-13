package net.bodz.bas.io.process;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class RunData {

    ByteArrayOutputStream input;
    ByteArrayOutputStream output;
    ByteArrayOutputStream error;
    int exitStatus;

    // IDataSupplyListener inputCapture = (buf, off, len) -> output.write(buf, off, len);

    public RunData captureOutput(ProcessWrapper processWrapper) {
        output = new ByteArrayOutputStream();
        processWrapper.captureOutput((buf, off, len) -> output.write(buf, off, len));
        return this;
    }

    public RunData captureError(ProcessWrapper processWrapper) {
        error = new ByteArrayOutputStream();
        processWrapper.captureError((buf, off, len) -> error.write(buf, off, len));
        return this;
    }

    public RunData captureOutputAndError(ProcessWrapper processWrapper) {
        output = new ByteArrayOutputStream();
        processWrapper.captureOutput((buf, off, len) -> output.write(buf, off, len));
        processWrapper.captureError((buf, off, len) -> output.write(buf, off, len));
        return this;
    }

    public byte[] getInput() {
        return input == null ? null : input.toByteArray();
    }

    public final String getInputText() {
        return getInputText((Charset) null);
    }

    public final String getInputText(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getInputText(charset);
    }

    public String getInputText(Charset charset) {
        byte[] bytes = getInput();
        if (bytes == null)
            return null;
        if (charset == null)
            return new String(bytes);
        else
            return new String(bytes, charset);
    }

    public byte[] getOutput() {
        return output == null ? null : output.toByteArray();
    }

    public final String getOutputText() {
        return getOutputText((Charset) null);
    }

    public final String getOutputText(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getOutputText(charset);
    }

    public String getOutputText(Charset charset) {
        byte[] bytes = getOutput();
        if (bytes == null)
            return null;
        if (charset == null)
            return new String(bytes);
        else
            return new String(bytes, charset);
    }

    public byte[] getError() {
        return error == null ? null : error.toByteArray();
    }

    public final String getErrorText() {
        return getErrorText((Charset) null);
    }

    public final String getErrorText(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getErrorText(charset);
    }

    public String getErrorText(Charset charset) {
        byte[] bytes = getError();
        if (bytes == null)
            return null;
        if (charset == null)
            return new String(bytes);
        else
            return new String(bytes, charset);
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    public boolean isSuccess() {
        return exitStatus == 0;
    }

    public boolean isFailed() {
        return exitStatus != 0;
    }

}
