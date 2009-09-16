package net.bodz.xfo.core;

import org.junit.Test;

public class FSMTest {

    @Test
    public void test() throws Exception {
        FSM fsm = new FSM();

        int COMMENT = fsm.prepare("COMMENT", FSM.START, "/*");
        fsm.prepare(COMMENT, "*/", FSM.START);

        int STRING = fsm.prepare("STRING", FSM.START, "\"");
        fsm.prepare(STRING, "\"", FSM.START);

        System.out.println("FSM Dump");
        System.out.println(fsm);
    }

}
