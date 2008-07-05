package net.bodz.bas.log;

public interface ILogOut {

    void p(Object obj);

    void p(Object... args);

    void P(Object obj);

    void P(Object... args);

    void pf(String format, Object... args);

    void PF(String format, Object... args);

    void printcr();

    void sig(Object obj);

    void sig(Object... args);

    void sigf(String format, Object... args);

}
