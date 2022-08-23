package net.bodz.bas.log.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.NamePatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

import net.bodz.bas.t.tuple.Split;

@Plugin(name = "ShortLocationPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "sl" })
public class ShortLocationPatternConverter
        extends NamePatternConverter
        implements
            LocationAware {

    private static final String NA = "?";

    private ShortLocationPatternConverter(String[] options) {
        super("Short Location", "shortLocation", options);
    }

    public static ShortLocationPatternConverter newInstance(String[] options) {
        return new ShortLocationPatternConverter(options);
    }

    @Override
    public void format(final LogEvent event, final StringBuilder output) {
        StackTraceElement element = event.getSource();
        if (element == null) {
            output.append(NA);
            return;
        }
        String className = element.getClassName();
        if (className == null) { // assert false.
            output.append(NA);
            return;
        }
        String fileName = element.getFileName();
        if (fileName == null) {
            abbreviate(className, output);
            return;
        }

        String simpleName = Split.packageName(className).b;
        String baseName = Split.nameExtension(fileName).a;
        boolean overlapped = simpleName.equals(baseName);

        if (overlapped) {
            StringBuilder sb = new StringBuilder();
            abbreviate(className, sb);
            int leftPos = sb.lastIndexOf(".");
            if (leftPos == -1)
                leftPos = 0;
            sb.delete(leftPos, sb.length());
            output.append(sb);
        } else {
            abbreviate(className, output);
        }
        output.append(" (");
        output.append(fileName);
        output.append(":");
        output.append(element.getLineNumber());
        output.append(")");
    }

    @Override
    public boolean requiresLocation() {
        return true;
    }

}
