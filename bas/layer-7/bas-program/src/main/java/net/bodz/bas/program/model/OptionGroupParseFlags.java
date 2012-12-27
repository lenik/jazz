package net.bodz.bas.program.model;

public class OptionGroupParseFlags {

    boolean stopAtFirstNonOption = true;

    public boolean isStopAtFirstNonOption() {
        return stopAtFirstNonOption;
    }

    public void setStopAtFirstNonOption(boolean stopAtFirstNonOption) {
        this.stopAtFirstNonOption = stopAtFirstNonOption;
    }

    public static OptionGroupParseFlags DEFAULT = new OptionGroupParseFlags();

}
