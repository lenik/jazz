package net.bodz.bas.ant.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Task;

import net.bodz.bas.log.Logger;
import net.bodz.bas.meta.source.UnderDevelopment;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.potato.element.PropertyWriteException;
import net.bodz.bas.program.model.IOptionGroup;
import net.bodz.bas.program.skel.BasicCLI;

public class CLITask
        extends Task {

    protected final BasicCLI program;
    protected final IType appType;

    public CLITask(BasicCLI app) {
        this.program = app;
        this.appType = app.getPotatoType();
    }

    private List<String> remainingArguments;
    private String tailArguments;
    private int logLevel = 0;

    protected void addArguments(String... args) {
        if (remainingArguments == null)
            remainingArguments = new ArrayList<String>();
        remainingArguments.addAll(Arrays.asList(args));
    }

    public String getCLITail() {
        return tailArguments;
    }

    public void setCLITail(String tailargs) {
        this.tailArguments = tailargs;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    protected Object get(String cliFieldName) {
        try {
            return appType.getProperty(cliFieldName).read(program);
        } catch (PropertyReadException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected void set(String cliFieldName, Object newValue) {
        try {
            appType.getProperty(cliFieldName).write(program, newValue);
        } catch (PropertyWriteException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @UnderDevelopment
    public void _maybeConfigure()
            throws BuildException {
        RuntimeConfigurable wrapper = getRuntimeConfigurableWrapper();
        Hashtable<?, ?> attrs = wrapper.getAttributeMap();

        IOptionGroup opts = program.getOptionModel();
        List<String> rawArgs = null;
        for (Map.Entry<?, ?> ent : attrs.entrySet()) {
            String optnam = (String) ent.getKey();
            String optarg = (String) ent.getValue();
            if (optnam.startsWith("arg-")) {
                int argIndex = Integer.parseInt(optnam.substring(4));
                if (rawArgs == null)
                    rawArgs = new ArrayList<>(argIndex + 1);
                while (rawArgs.size() <= argIndex)
                    rawArgs.add(null);
                rawArgs.set(argIndex, optarg);
                continue;
            }

            List<String> suggestions = new ArrayList<>();
            opts.fillSuggestKeys(optnam, suggestions);
            if (suggestions.size() != 1) {
                // TODO
            }

            // if (optnam.length() == 1)
            // opts.load(app, "-" + optnam + optarg);
            // else
            // opts.load(app, "--" + optnam, optarg);
            wrapper.removeAttribute(optnam);
        }
        if (rawArgs != null) {
            for (int i = rawArgs.size() - 1; i >= 0; i--)
                if (rawArgs.get(i) == null)
                    rawArgs.remove(i);
            String[] argv = rawArgs.toArray(new String[0]);
            ; // app.addArguments(argv);
        }

        // children tags
        Enumeration<RuntimeConfigurable> children = wrapper.getChildren();
        while (children.hasMoreElements()) {
            RuntimeConfigurable child = children.nextElement();
            String name = child.getElementTag();
            String value = child.getText().toString();
            if ("arg".equals(name))
                ; // app.addArguments(value);
            else
                ;
            // throw new BuildException("invalid child tag: " + name);
        }

        super.maybeConfigure();
    }

    @Override
    public void execute()
            throws BuildException {
        try {
            if (remainingArguments != null && !remainingArguments.isEmpty()) {
                String[] moreargv = remainingArguments.toArray(new String[0]);
                program.receive(moreargv);
            }
            // adapting attributes
            if (logLevel != 0) {
                Logger logger = (Logger) appType.getProperty("logger").read(program);
                logger.setDelta(logLevel);
            }
        } catch (Exception e) {
            throw new BuildException(e.getMessage(), e);
        }
        try {
            program.runExtra(tailArguments);
        } catch (Throwable e) {
            throw new BuildException(e.getMessage(), e);
        }
    }

    private final StringBuilder errbuf = new StringBuilder();

    @Override
    protected void handleErrorOutput(String output) {
        errbuf.append(output);
        if (output.endsWith("\n")) {
            super.handleErrorOutput(errbuf.toString());
            errbuf.setLength(0);
        }
    }

}
