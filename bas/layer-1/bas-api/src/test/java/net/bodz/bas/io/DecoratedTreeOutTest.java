package net.bodz.bas.io;

import org.junit.Test;

import net.bodz.bas.io.impl.TreeOutImpl;

public class DecoratedTreeOutTest {

    class Dto
            extends DecoratedTreeOut {

        public Dto(ITreeOut _orig) {
            super(_orig);
        }

    }

    @Test
    public void test() {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        Dto dto = new Dto(out);
        out = dto;

        out.println("first");

        out.enterln("bar");
        out.println("This is bar content.");
        out.leave();

        out.println("second");
    }

}
