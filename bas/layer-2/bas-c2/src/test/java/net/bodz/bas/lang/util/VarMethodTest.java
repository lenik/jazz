package net.bodz.bas.lang.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.types.testtypes.C;
import net.bodz.bas.types.testtypes.CI;
import net.bodz.bas.types.testtypes.CJz;
import net.bodz.bas.types.testtypes.CJzKI;
import net.bodz.bas.types.testtypes.Cat;
import net.bodz.bas.types.testtypes.D;
import net.bodz.bas.types.testtypes.DIJ;
import net.bodz.bas.types.testtypes.DIJy;
import net.bodz.bas.types.testtypes.Dog;
import net.bodz.bas.types.testtypes.Dx;
import net.bodz.bas.types.testtypes.DxKI;
import net.bodz.bas.types.testtypes.DxKIx;

import org.junit.Test;

public class VarMethodTest {

    public String play(Cat a, Dog b) {
        return "Cat, Dog"; 
    }

    public String play(C a, Dog b) {
        return "C, Dog"; 
    }

    public String play(Cat a, D b) {
        return "Cat, D"; 
    }

    public String play(C a, D b) {
        return "C, D"; 
    }

    public String play(CI a, Dx b) {
        return "CI, Dx"; 
    }

    public String play(CI a, DxKI b) {
        return "CI, DxKI"; 
    }

    public String play(CJz a, DxKI b) {
        return "CJz, DxKI"; 
    }

    public String play(CJz a, DIJy b) {
        return "CJz, DIJy"; 
    }

    public String play(CJzKI a, DIJ b) {
        return "CJzKI, DIJ"; 
    }

    VarMethod playf;
    {
        playf = new VarMethod("play", Members.publicMethods( 
                VarMethodTest.class, "play")); 
    }

    @Test
    public void test1() throws Exception {
        Object ret = playf.invoke(this, new CI(), new DxKIx());
        assertEquals("CI, DxKI", ret); 
    }

    @Test(expected = NoSuchMethodException.class)
    public void test2() throws Exception {
        playf.invoke(this, new CJzKI(), new Dx());
    }

}
