package net.bodz.bas.text.flatf;

import net.bodz.bas.err.ParseException;

public interface ISectionHandler {

    /**
     * Process instructions.
     * 
     * @param command
     *            The command name of the instruction.
     * @param data
     *            The payload of the instruction.
     * @return <code>false</code> if the process-instruction is not recognized and skipped by the
     *         implementation.
     */
    boolean pi(String command, String data)
            throws ParseException;

    /**
     * Invoked on the beginning of a section.
     * 
     * @param name
     *            Name of the section.
     */
    void sectionBegin(String name);

    /**
     * Invoked on the end of a section. For Flat-file, there is no end-of-section marker. So this
     * method is invoked just before a new section is began.
     * 
     * @param name
     *            Name of the section.
     */
    void sectionEnd(String name);

    /**
     * Process attribute in a section.
     * 
     * @param name
     *            The attribute name.
     * @param string
     *            The attribute data.
     */
    void attribute(String name, String string)
            throws ParseException;

}
