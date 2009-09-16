package net.bodz.xfo.core;

import java.io.File;

/**
 * ADT: Alternative Data Stream
 */
public class ADSTaggedFile extends TaggedFile {

    public static final String TAGS_MAP    = ":TAGS.map";
    public static final String SUMMARY     = ":\u0005SummaryInformation";
    public static final String DOC_SUMMARY = ":\u0005DocumentSummaryInformation";

    public ADSTaggedFile(File file) {
        super(file, new File(file.getName() + TAGS_MAP));
    }

}
