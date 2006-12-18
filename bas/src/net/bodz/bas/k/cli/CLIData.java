package net.bodz.bas.k.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CLIData {

    /** 未使用的参数 */
    protected List<String> arguments;

    public CLIData(String[] arguments) {
        // assert arguments != null;
        this.arguments = new ArrayList<String>();
        for (String argument : arguments) {
            this.arguments.add(argument);
        }
    }

    public CLIData(List<String> arguments, boolean copy) {
        // assert arguments != null;
        if (copy)
            this.arguments = new ArrayList<String>(arguments);
        else
            this.arguments = arguments;
    }

    public CLIData(List<String> arguments) {
        this(arguments, false);
    }

    /** 获取未分析的剩余参数 */
    public List<String> getArguments() {
        return arguments;
    }

    /** 重定义以支持共享模式 */
    protected String remove(int index) {
        return arguments.remove(index);
    }

    public void fill(Object CLIObject) {
        //
    }

    public void fill(Map<String, Object> map) {
        // 
    }

    public Map<String, Object> parse() {
        Map<String, Object> map = new HashMap<String, Object>();
        fill(map);
        return map;
    }

    public String help() {
        StringBuffer page = new StringBuffer();
        return page.toString();
    }

    // 

}
