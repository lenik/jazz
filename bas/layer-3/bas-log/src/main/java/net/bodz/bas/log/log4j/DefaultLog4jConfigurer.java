package net.bodz.bas.log.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

public class DefaultLog4jConfigurer
        implements
            ILog4jConfigurer {

    public static boolean showHeader = true;

    public DefaultLog4jConfigurer() {
    }

    @Override
    public int getPriority() {
        return -1000;
    }

    class Fx {
        int verbose;
        String dateTime;
        String channel;
        String location;
        String text;

        public Fx(int verbose, String dateTime, String channel, String location, String text) {
            this.verbose = verbose;
            this.dateTime = dateTime;
            this.channel = channel;
            this.location = location;
            this.text = text;
        }

        @Override
        public String toString() {
            String s = "";
            if (showHeader) {
                if (verbose >= 0)
                    s = esc(dateTime) + "%sd" + esc(0) //
                            + " " + esc(channel) + "[%t/%level]" + esc(0);
                if (verbose >= 1)
                    s += esc(location) + " %sl{1.} " + esc(0);
                if (verbose >= 2)
                    s += "\n       ";
                if (verbose >= 0)
                    s += " " + esc(text) + "%msg%n%throwable" + esc(0);
            }
            return s;
        }

    }

    static boolean ansiFx = true;

    static String esc(Object cmd) {
        if (ansiFx)
            return "\u001b[" + cmd + "m";
        else
            return "";
    }

    Fx LIGHT = new Fx(1, "37", "47;35", "37", "33");
    Fx NORMAL = new Fx(1, "37", "47;35", "21;37", "0");
    Fx WARN = new Fx(2, "37", "41;37", "21;31", "35");
    Fx ERROR = new Fx(2, "37", "41;37", "21;31", "31");

    @Override
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
//        builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
//                .addAttribute("level", Level.DEBUG));

        AppenderComponentBuilder appenderBuilder = builder.newAppender("stdout", "CONSOLE")//
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT) //

                .add(builder.newLayout("PatternLayout")//
                        .addComponent(builder.newComponent("LevelPatternSelector") //
                                .addAttribute("defaultPattern", LIGHT) //
                                .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "INFO")
                                        .addAttribute("pattern", NORMAL)) //
                                .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "WARN")
                                        .addAttribute("pattern", WARN)) //
                                .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "ERROR")
                                        .addAttribute("pattern", ERROR)) //
                        )) //
                .add(builder.newFilter("MarkerFilter", Filter.Result.DENY, Filter.Result.NEUTRAL)//
                        .addAttribute("marker", "FLOW"));

        builder.add(appenderBuilder);

        builder.add(rootLogger(builder, Level.WARN, "stdout"));
    }

}
