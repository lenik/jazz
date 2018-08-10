package net.bodz.bas.scanner.ep6000;

import java.io.IOException;

import net.bodz.bas.comm.SerialSupport;

public class SerialEp6000
        extends SerialSupport
        implements IEp6000 {

    public static final int BAUD_RATE = 115200;

    int scanMaxWait = 100;
    int scanInterval = 1;

    public SerialEp6000()
            throws IOException {
        super(BAUD_RATE);
    }

    @Override
    public int getParameter(NumParam parameter)
            throws Ep6000Exception, IOException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetParameter);
        tx.writeWord(parameter.getCode());
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readParameterAndVerify(parameter.code);
        int value = rx.readWord();
        rx.end();
        return value;
    }

    @Override
    public byte[] getParameter(DataParam parameter)
            throws IOException, Ep6000Exception {
        TxPacket tx = TxPacket.start(out, CommandCode.GetParameter);
        tx.writeWord(parameter.getCode());
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readParameterAndVerify(parameter.code);
        byte[] data = rx.readData();
        rx.end();
        return data;
    }

    @Override
    public void setParameter(NumParam parameter, int value, boolean storage)
            throws IOException, Ep6000Exception {
        TxPacket tx = TxPacket.start(out, CommandCode.SetParameter);
        tx.writeWord(parameter.getCode());
        tx.writeWord(value);
        tx.writeByte(storage ? 1 : 0);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void startDecode()
            throws IOException, Ep6000Exception {
        TxPacket tx = TxPacket.start(out, CommandCode.StartDecode);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void stopDecode()
            throws IOException, Ep6000Exception {
        TxPacket tx = TxPacket.start(out, CommandCode.StopDecode);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

}
