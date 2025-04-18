package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.cli.Command;
import net.bodz.bas.cli.IArgQueue;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.ISettingParsable;

public class CommonCmds
        extends AbstractNioCommandProvider {

    List<ISettingParsable> settingSources = new ArrayList<>();

    public CommonCmds(@NotNull SocketChannel channel) {
        super(channel);
    }

    public void addSettingSource(@NotNull ISettingParsable source) {
        settingSources.add(source);
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "echo":
                echo(cmd);
                return true;

            case "echoli":
                echoli(cmd);
                return true;

            case "set":
                set(cmd);
                return true;

            case "show":
                if ("vars".equals(cmd.peek())) {
                    cmd.shift();
                    showVariables(cmd);
                    return true;
                }
        }
        return false;
    }

    void echo(IArgQueue args)
            throws IOException {
        println(args.toString(" "));
    }

    void echoli(IArgQueue args)
            throws IOException {
        String[] array = args.toArray();
        for (int i = 0; i < array.length; i++) {
            String el = array[i];
            el = el.replace("\\", "\\\\");
            el = el.replace("\n", "\\n");
            el = el.replace("\r", "\\r");
            el = el.replace("\0", "\\0");
            String line = String.format("    %d. %s%c", i, el, i == array.length - 1 ? '.' : ';');
            println(line);
        }
    }

    void set(IArgQueue args)
            throws IOException, ParseException {
        String name = args.shift();
        if (name == null) {
            error("expect variable name, one of following:");
            for (ISettingParsable source : settingSources)
                error("    - " + source.getSettingNames());
            return;
        }

        for (ISettingParsable source : settingSources)
            if (source.setSetting(name, args)) {
                return;
            }
        error("invalid setting name: " + name);
    }

    void showVariables(IArgQueue args)
            throws IOException {
        String pattern = args.shift();
        for (ISettingParsable source : settingSources) {
            for (String name : source.getSettingNames()) {
                if (pattern != null)
                    if (!name.contains(pattern))
                        continue;
                Object value = source.getSetting(name);
                println(name + " = " + value);
            }
        }
    }

}
