package net.bodz.bas.log.log4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.DatePatternConverter;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import org.apache.logging.log4j.core.time.Instant;

@Plugin(name = "ShortDatePatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "sd" })
public class ShortDatePatternConverter
        extends LogEventPatternConverter {

    DatePatternConverter impl;

    long lastDay;

    private ShortDatePatternConverter(DatePatternConverter impl) {
        super("Short Date", "shortDate");
        this.impl = impl;
    }

    public static ShortDatePatternConverter newInstance(String[] options) {
        // System.out.println("NEW INSTANCE: " + Arrays.asList(options));
        DatePatternConverter impl = DatePatternConverter.newInstance(options);
        return new ShortDatePatternConverter(impl);
    }

    @Override
    public void format(final LogEvent event, final StringBuilder output) {
        Instant instant = event.getInstant();
        long epochSecond = instant.getEpochSecond();
        long epochMinute = epochSecond / 60;
        long epochHour = epochMinute / 60;
        long epochDay = epochHour / 24;
        if (epochDay != lastDay) {
            impl.format(event, output);
            lastDay = epochDay;
        } else {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss,SSS");
            String timeStr = timeFormat.format(instant.getEpochMillisecond());
            output.append(timeStr);
            int nano = instant.getNanoOfMillisecond();
            if (nano != 0)
                output.append("." + nano);
        }
    }

}
