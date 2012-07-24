package net.bodz.bas.cli.model;

import java.util.StringTokenizer;

import net.bodz.bas.err.ParseException;

/**
 * @option -D --define =NAM=VAL --stdout hidden weak
 */
public class OptionDescriptor {

    public void parse(AbstractOption option, String script)
            throws ParseException {
        StringTokenizer tokens = new StringTokenizer(script, " ");

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.isEmpty())
                continue;

            if (token.startsWith("-")) {
                do
                    token = token.substring(1);
                while (token.startsWith("-"));
                option.addAlias(token);
                continue;
            }

            if (token.startsWith("=")) {
                String hint = token.substring(1);
                option.setValueHint(hint);
                continue;
            }

            String parameter = null;
            int eq = token.indexOf('=');
            if (eq != -1) {
                parameter = token.substring(eq + 1);
                token = token.substring(0, eq);
            }

            switch (token) {
            case "arg-position":
                try {
                    int argPosition = Integer.parseInt(parameter);
                    option.setArgPosition(argPosition);
                } catch (NumberFormatException e) {
                    throw new ParseException("Argument position should be integer: " + parameter, e);
                }
                break;

            case "default":
                Object defaultValue = null;
                // TODO defaultValue = option.parse(parameter);
                option.setDefaultValue(defaultValue);
                break;

            case "hidden":
                option.setHidden(true);
                break;

            case "priority":
                try {
                    int priority = Integer.parseInt(parameter);
                    option.setPriority(priority);
                } catch (NumberFormatException e) {
                    throw new ParseException("Priority should be integer: " + parameter, e);
                }
                break;

            case "required":
                option.setRequired(true);
                break;

            case "weak":
                int priority = option.getPriority();
                option.setPriority(priority + 1);
                break;

            default:
                throw new ParseException("Bad option modifier: " + token);
            }
        } // while
    }

}
