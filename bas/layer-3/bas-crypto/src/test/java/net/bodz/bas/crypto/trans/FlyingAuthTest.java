package net.bodz.bas.crypto.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.crypto.trans.IFlyingSupport.tr;
import net.bodz.bas.crypto.trans.fn.ICodeBin;
import net.bodz.bas.crypto.trans.fn.PartialMd5Transformer;

public class FlyingAuthTest
        extends Assert {

    int window = 1_000; // 1 second
    IFlyingTransient fTransient = new EpochTransient(window);

    int md5Len = 6;
    int md5Radix = 10;
    IFlyingTransient fMd5Time = fTransient.transform(tr.partialMd5(md5Len, md5Radix));
    IFlyingTransient core = fMd5Time;

    int distance = 10 * 60; // 10 minutes
    int allowAhead = 3 * 60; // 3 minutes

    static PartialMd5Transformer partialMd5 = tr.partialMd5(6, 10);

    FlyingAuth smsAuth = new FlyingAuth(core, //
            s -> tr.md5Sign(s).andThen(partialMd5), //
            distance, allowAhead);

    @Test
    public void testTransient() {
        ICodeBin code = fTransient.getCodeAtTime(33_123);
        assertEquals("33", code.toString());
    }

    @Test
    public void testAuth() {
        String content = "13812345678";
        IFlyingTransient fSign = smsAuth.sign(content);
        List<String> codes = new ArrayList<>();
        for (int i = 100; i < 200; i++) {
            ICodeBin code = fSign.getCode(i);
            codes.add(code.getStringForm());
        }

        String[] expects = "351732, 834732, 733581, 351907, 779481, 783371, 289313, 437863, 531622, 578161, 298279, 306436, 078246, 189289, 190647, 200944, 623798, 576008, 966951, 397202, 703753, 523709, 329677, 112754, 711977, 551060, 058469, 767180, 595865, 745515, 746909, 768102, 347291, 223415, 654745, 009056, 570749, 457591, 501006, 751201, 048835, 930787, 986646, 405023, 557863, 094689, 296716, 623638, 214930, 732425, 492186, 716948, 717838, 042242, 125637, 203798, 360292, 905278, 935749, 403791, 210455, 679294, 890456, 181696, 956731, 967839, 707201, 747215, 017737, 866607, 039634, 660879, 045017, 942160, 849470, 701509, 038769, 653773, 266628, 312593, 124099, 572238, 104247, 135591, 002612, 589543, 501203, 208604, 013657, 506684, 825625, 591987, 001617, 141066, 070638, 025892, 754671, 498165, 339529, 280908"
                .split(", ");
        assertEquals(Arrays.asList(expects), codes);

        FlyingIndex fIndex = fSign.lastIndexOf(100, "779481", distance, allowAhead);
        assertEquals(104, fIndex.getIndex());
    }

}
