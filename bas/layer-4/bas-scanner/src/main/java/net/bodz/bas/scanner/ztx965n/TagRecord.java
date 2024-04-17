package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;
import java.time.LocalDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;

public class TagRecord
        implements
            IRxParser {

    LocalDateTime startTime;
    LocalDateTime endTime;
    int readCount;
    int antenna;
    TagType tagType;
    Words epc;

    @Override
    public TagRecord parse(RxPacket in)
            throws IOException {
        try {
            startTime = in.readTime();
            endTime = in.readTime();
            readCount = in.readWord();
            antenna = in.readByte();
            tagType = TagType.forCode(in.readByte());
            epc = new Words().parse(in);
        } catch (IOException e) {
            throw new UnexpectedException();
        }
        return this;
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut();
        out.println("start-time: " + DateTimes.ISO_LOCAL_DATE_TIME.format(startTime));
        out.println("end-time: " + DateTimes.ISO_LOCAL_DATE_TIME.format(endTime));
        out.println("read-count: " + readCount);
        out.println("antenna: " + antenna);
        out.println("tag-type: " + tagType);
        out.println("epc: " + epc);
        return out.toString();
    }

}
