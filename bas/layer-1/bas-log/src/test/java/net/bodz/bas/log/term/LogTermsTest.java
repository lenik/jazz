package net.bodz.bas.log.term;

import static org.junit.Assert.assertEquals;

import net.bodz.bas.log.term.ConsoleLogTerm;
import net.bodz.bas.log.term.LogTerm;
import net.bodz.bas.log.term.LogTerms;

import org.junit.Test;

public class LogTermsTest {

    @Test
    public void test1() {
        LogTerm bas = new ConsoleLogTerm();
        LogTerm t1 = new ConsoleLogTerm();
        LogTerms.set("net.bodz.bas", bas); 
        LogTerms.set(1, t1);

        LogTerm byname = LogTerms.get(LogTermsTest.class.getName());
        LogTerm bycaller = LogTerms.get(1);

        assertEquals(bas, byname);
        assertEquals(t1, bycaller);
    }

}
