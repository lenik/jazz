package net.bodz.bas.parser;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class Command {

    String name;
    List<String> args = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public List<String> getArguments() {
        return args;
    }

    public void setArguments(@NotNull List<String> args) {
        this.args = args;
    }

    @NotNull
    public String getArgument(int index) {
        return args.get(index);
    }

    public void addArgument(@NotNull String arg) {
        args.add(arg);
    }

    public int getArgumentCount() {
        return args.size();
    }

    public boolean isNoArgument() {
        return args.isEmpty();
    }

    public boolean isNoArgument(int index) {
        return !isArgumentPresent(index);
    }

    public boolean isArgumentPresent() {
        return !args.isEmpty();
    }

    public boolean isArgumentPresent(int index) {
        if (index < 0 || index >= args.size())
            return false;
        String arg = args.get(index);
        return arg != null;
    }

    @NotNull
    public String getArgumentsLine() {
        StringBuilder buf = new StringBuilder();
        int n = 0;
        for (String arg : args) {
            if (n != 0)
                buf.append(" ");
            buf.append(arg);
            n++;
        }
        return buf.toString();
    }

    @NotNull
    public String getCommandLine() {
        StringBuilder buf = new StringBuilder();
        buf.append(name);
        for (String arg : args)
            buf.append(" ").append(arg);
        return buf.toString();
    }

}