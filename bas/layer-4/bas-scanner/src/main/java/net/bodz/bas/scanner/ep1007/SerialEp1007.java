package net.bodz.bas.scanner.ep1007;

import java.io.IOException;

import net.bodz.bas.comm.SerialSupport;

public class SerialEp1007
        extends SerialSupport
        implements IEp1007 {

    public static final int BAUD_RATE = 115200;

    public SerialEp1007()
            throws IOException {
        super(BAUD_RATE);
    }

    @Override
    public void enterSetMode()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.YSET);
        tx.end();
    }

    @Override
    public void saveAndExit()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.YEND);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void startDecode()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.TRIGGER_SCAN);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        // TODO rx.readDataAndCheck();
        rx.end();
    }

    @Override
    public void stopDecode()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.STOP_SCAN);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void factoryReset()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.FACTORY_DEFAULTS);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void loadCustomDefaults()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.CUSTOM_DEFAULTS);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void saveCustomDefaults()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.WR_CUSTOM_DEFAULTS);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void readRevision()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.READ_REVISION);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void setContinuousMode(boolean enabled)
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, //
                enabled ? CommandStr.SetDecodeContinuous : CommandStr.SetDecodeOnce);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void setRepeatMode(boolean enabled)
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, //
                enabled ? CommandStr.EnableRepeatDecode : CommandStr.DisableRepeatDecode);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void switchRepeatMode()
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, CommandStr.SwitchRepeatDecode);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void setKeyboardMode(boolean enabled)
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out,//
                enabled ? CommandStr.KeyboardOut : CommandStr.SerialOut);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void setRequireAck(boolean required)
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, //
                required ? CommandStr.RequireAck : CommandStr.NoAck);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

    @Override
    public void enableAllBarcodes(boolean state)
            throws IOException, Ep1007Exception {
        TxPacket tx = TxPacket.start(out, //
                state ? CommandStr.EnableAllBarcode : CommandStr.DisableAllBarcode);
        tx.end();
        RxPacket rx = RxPacket.start(in, tx);
        rx.end();
    }

}
