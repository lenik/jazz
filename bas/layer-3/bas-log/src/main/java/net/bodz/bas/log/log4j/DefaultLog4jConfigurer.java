package net.bodz.bas.log.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;

public class DefaultLog4jConfigurer
        implements ILog4jConfigurer {

    public static final boolean showHeader = true;
    public static final boolean colorEffects = true;

    public DefaultLog4jConfigurer() {
    }

    @Override
    public int getPriority() {
        return -1000;
    }

    static boolean ansiFx = true;

    AnsiPattern LIGHT = new AnsiPattern(showHeader, 2, colorEffects, "37", "47;35", "37", "33");
    AnsiPattern NORMAL = new AnsiPattern(showHeader, 2, colorEffects, "37", "47;35", "21;37", "0");
    AnsiPattern WARN = new AnsiPattern(showHeader, 2, colorEffects, "37", "41;37", "21;31", "35");
    AnsiPattern ERROR = new AnsiPattern(showHeader, 2, colorEffects, "37", "41;37", "21;31", "31");

    @Override
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
//        builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
//                .addAttribute("level", Level.DEBUG));

        AppenderComponentBuilder stdoutFx = builder //
                .newAppender(ansiAppender, "CONSOLE")//
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT) //
                .add(fxLayout(builder)) //
                .add(builder.newFilter("MarkerFilter", Filter.Result.DENY, Filter.Result.NEUTRAL)//
                        .addAttribute("marker", "FLOW"));
        builder.add(stdoutFx);

        AppenderComponentBuilder stderrFx = builder //
                .newAppender(ansiErrorAppender, "CONSOLE")//
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_ERR) //
                .add(fxLayout(builder)) //
                .add(builder.newFilter("MarkerFilter", Filter.Result.DENY, Filter.Result.NEUTRAL)//
                        .addAttribute("marker", "FLOW"));
        builder.add(stderrFx);

        builder.add(rootLogger(builder, Level.WARN, ansiErrorAppender));
    }

    LayoutComponentBuilder fxLayout(ConfigurationBuilder<? extends Configuration> builder) {
        LayoutComponentBuilder layout = builder.newLayout("PatternLayout")//
                .addComponent(builder.newComponent("LevelPatternSelector") //
                        .addAttribute("defaultPattern", LIGHT) //
                        .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "INFO").addAttribute("pattern", NORMAL)) //
                        .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "WARN").addAttribute("pattern", WARN)) //
                        .addComponent(builder.newComponent("PatternMatch").addAttribute("key", "ERROR").addAttribute("pattern", ERROR)) //
                );
        return layout;
    }

}

class AnsiPattern {

    boolean showHeader;
    int verbose;

    boolean colorEffects;
    String dateTimeColor;
    String channelColor;
    String locationColor;
    String textColor;

    public AnsiPattern(boolean showHeader, int verbose, boolean colorEffects, String dateTimeColor, String channelColor, String locationColor, String textColor) {
        this.showHeader = showHeader;
        this.verbose = verbose;
        this.colorEffects = colorEffects;
        this.dateTimeColor = dateTimeColor;
        this.channelColor = channelColor;
        this.locationColor = locationColor;
        this.textColor = textColor;
    }

    public String build() {
        StringBuilder buf = new StringBuilder(80);
        if (showHeader) {
            if (verbose >= 0) {
                buf.append(esc(dateTimeColor)).append("%sd").append(esc(0));
                buf.append(" ");
                buf.append(esc(channelColor)).append("[%t/%level]").append(esc(0));
            }
            if (verbose >= 1) {
//                buf.append(esc(locationColor)).append(" %F ").append(esc(0));
                buf.append(" %l %c{1.}(%M:%L)");
            }
            if (verbose >= 3)
                buf.append("\n       ");
            if (verbose >= 0) {
                buf.append(" ");
            }
            buf.append(esc(textColor)).append("%msg%n%throwable").append(esc(0));
        }
        String pattern = buf.toString();
        return pattern;
    }

    @Override
    public String toString() {
        return build();
    }

    String esc(Object cmd) {
        if (colorEffects)
            return "\u001b[" + cmd + "m";
        else
            return "";
    }

}
