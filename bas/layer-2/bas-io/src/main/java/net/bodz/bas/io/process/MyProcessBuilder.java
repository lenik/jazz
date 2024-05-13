package net.bodz.bas.io.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyProcessBuilder
        extends NonFinalProcessBuilder<MyProcessBuilder> {

    String name;
    IDataSupplyListener inputDataSupplyListener;
    IDataReceiveListener outputDataReceiveListener;
    IDataReceiveListener errorDataReceiveListener;

    public MyProcessBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MyProcessBuilder shell(String cmdline)
            throws IOException {
        if (cmdline == null)
            throw new NullPointerException("cmdline");
        if (name == null)
            name = "SHELL: " + cmdline;
        List<String> cmdv = new ArrayList<>(OsShell.shellCmdv);
        cmdv.add(cmdline);
        super.command(cmdv);
        return this;
    }

    @Override
    public MyProcessBuilder command(List<String> command) {
        if (name == null)
            name = "Process: " + command.get(0);
        return super.command(command);
    }

    @Override
    public MyProcessBuilder command(String... command) {
        if (name == null)
            name = "Process: " + command[0];
        return super.command(command);
    }

    public MyProcessBuilder supply(IDataSupplyListener listener) {
        this.inputDataSupplyListener = listener;
        return this;
    }

    public MyProcessBuilder captureOutput(IDataReceiveListener listener) {
        this.outputDataReceiveListener = listener;
        return this;
    }

    public MyProcessBuilder captureError(IDataReceiveListener listener) {
        this.errorDataReceiveListener = listener;
        return this;
    }

    public ProcessWrapper start()
            throws IOException {
        Process process = super._start();
        ProcessWrapper wrapper = new ProcessWrapper(process, name);
        if (inputDataSupplyListener != null)
            wrapper.supplyInput(inputDataSupplyListener);
        if (outputDataReceiveListener != null)
            wrapper.captureOutput(outputDataReceiveListener);
        if (errorDataReceiveListener != null)
            wrapper.captureError(errorDataReceiveListener);
        return wrapper;
    }

}
