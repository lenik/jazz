package net.bodz.bas.log.term;

import junit.framework.TestCase;

import org.junit.Test;

public class LogTermsTest
        extends TestCase {

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
