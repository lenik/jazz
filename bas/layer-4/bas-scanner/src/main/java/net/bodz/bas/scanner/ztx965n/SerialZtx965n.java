package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.comm.SerialSupport;

public class SerialZtx965n
        extends SerialSupport
        implements IZtx965n {

    public static final int BAUD_RATE = 115200;

    int scanMaxWait = 100;
    int scanInterval = 1;

    public SerialZtx965n()
            throws IOException {
        super(BAUD_RATE);
    }

    @Override
    public void setRs232BaudRate(int baudRate)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetRs232BaudRate);
        tx.writeByte(data.encodeBaudRate(baudRate));
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public ReaderInfo getReaderInfo()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetReaderInfo);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        ReaderInfo info = new ReaderInfo().parse(rx);
        rx.readDataAndCheck();
        return info;
    }

    static final byte[] emptyMask = {};

    public List<Words> scan()
            throws IOException, Ztx965nException {
        return scan(MemBank.EPC, 0, 0, emptyMask);
    }

    @Override
    public List<Words> scan(int memory, int start, int len, byte[] mask)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.Scan);
        tx.writeByte(memory);
        tx.writeWord(start);
        tx.writeByte(len);
        tx.write(mask);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        int nTags = rx.readByte() & 0xFF;
        List<Words> list = new ArrayList<Words>();
        for (int i = 0; i < nTags; i++) {
            Words words = new Words().parse(rx);
            list.add(words);
        }
        rx.readDataAndCheck();
        return list;
    }

    @Override
    public List<Words> scanBuf(int no, int count)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.ScanBuf);
        tx.writeByte(no);
        tx.writeByte(count);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        List<Words> list = new ArrayList<Words>();
        for (int i = 0; i < count; i++) {
            Words words = new Words().parse(rx);
            list.add(words);
        }
        rx.readDataAndCheck();
        return list;
    }

    @Override
    public byte[] readMemory(Words epc, int memBank, int start, int len, Password passwd)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.ReadMemory);
        tx.writeByte(epc.count);
        tx.write(epc.data);
        tx.writeByte(memBank);
        tx.writeByte(start / 2);
        tx.writeByte(len / 2);
        tx.write(passwd.data);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        byte[] data = rx.readDataAndCheck();
        return data;
    }

    @Override
    public void writeMemory(Words epc, int memBank, int start, byte[] data, Password passwd)
            throws IOException, Ztx965nException {
        if (data.length % 2 != 0)
            throw new IllegalArgumentException("Length isn't even.");
        TxPacket tx = TxPacket.start(out, CommandCode.WriteMemory);
        tx.writeByte(epc.count);
        tx.write(epc.data);
        tx.writeByte(memBank);
        tx.writeByte(start / 2);
        tx.writeByte(data.length / 2);
        tx.write(data);
        tx.write(passwd.data);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void lockMemory(Words epc, int memBank, int lock, Password passwd)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.LockMemory);
        tx.writeByte(epc.count);
        tx.write(epc.data);
        tx.writeByte(memBank);
        tx.writeByte(lock);
        tx.write(passwd.data);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void killTag(Words epc, Password passwd)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.KillTag);
        tx.writeByte(epc.count);
        tx.write(epc.data);
        tx.write(passwd.data);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void writeEpc(Integer address, Words epc, Password passwd)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.WriteEpc);
        tx.writeByte(epc.count);
        tx.write(epc.data);
        tx.write(passwd.data);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public EpcAndData readEpcAndData(Integer address, int memBank, int start, int len)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.ReadEpcAndData);
        tx.writeByte(memBank);
        tx.writeByte(start / 2);
        tx.writeByte(len / 2);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        Words epc = new Words().parse(rx);
        byte[] data = rx.readDataAndCheck();
        return new EpcAndData(epc, data);
    }

    @Override
    public void setRelayState(boolean relay1, boolean relay2)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetRelayState);
        int param = (relay1 ? 1 : 0) | (relay2 ? 2 : 0);
        tx.writeByte(param);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void setEmitPower(int emitPower)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetEmitPower);
        tx.writeByte(emitPower);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void setFreqChannel(int fmin, int fmax)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetFreqChannel);
        tx.writeByte(fmin);
        tx.writeByte(fmax);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public BaseConfig getBaseConfig()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetBaseWorkParam);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        BaseConfig config = new BaseConfig().parse(rx);
        rx.readDataAndCheck();
        return config;
    }

    @Override
    public void setBaseConfig(BaseConfig config)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetBaseWorkParam);
        config.send(tx);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void selectAntenna(int antenna)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SelectAntenna);
        tx.writeByte(antenna);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public boolean[] getRelayState()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetRelayState);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        int param = rx.readByte();
        boolean[] states = new boolean[2];
        states[0] = (param & 1) != 0;
        states[1] = (param & 2) != 0;

        rx.readDataAndCheck();
        return states;
    }

    @Override
    public void factoryReset()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.FactoryReset);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void resetReader()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.ResetReader);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void setAutoMode(boolean autoMode)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetAutoMode);
        tx.writeByte(autoMode ? 1 : 0);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public void clearMemory()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.ClearMemory);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public Calendar getReaderTime()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetReaderTime);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        Calendar time = rx.readTime();
        rx.readDataAndCheck();
        return time;
    }

    @Override
    public void setReaderTime(Calendar time)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetReaderTime);
        byte[] timeData = encodeTime(time);
        tx.write(timeData);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    static byte[] encodeTime(Calendar cal) {
        byte[] data = new byte[6];
        data[0] = (byte) (cal.get(Calendar.YEAR) - 1900);
        data[1] = (byte) cal.get(Calendar.MONTH);
        data[2] = (byte) cal.get(Calendar.DATE);
        data[3] = (byte) cal.get(Calendar.HOUR_OF_DAY);
        data[4] = (byte) cal.get(Calendar.MINUTE);
        data[5] = (byte) cal.get(Calendar.SECOND);
        return data;
    }

    @Override
    public ActiveConfig getActiveConfig()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetActiveWorkParam);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        ActiveConfig param = new ActiveConfig().parse(rx);
        rx.readDataAndCheck();
        return param;
    }

    @Override
    public void setActiveConfig(ActiveConfig config)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetActiveWorkParam);
        config.send(tx);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public SelectFilter getSelectFilter()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetSelectFilter);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        SelectFilter filter = new SelectFilter().parse(rx);
        rx.readDataAndCheck();
        return filter;
    }

    @Override
    public void setSelectFilter(SelectFilter filter)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetSelectFilter);
        filter.send(tx);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public NetConf getNetConf()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetNetConf);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        NetConf conf = new NetConf().parse(rx);
        rx.readDataAndCheck();
        return conf;
    }

    @Override
    public void setNetConf(NetConf conf)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.SetNetConf);
        conf.send(tx);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public String getMAC()
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetMAC);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        byte[] data = rx.readDataAndCheck();
        return StringArray.join(".", data);
    }

    @Override
    public void setMAC(String mac)
            throws IOException, Ztx965nException {
        String[] parts = StringArray.splitRaw(mac, '.');
        if (parts.length != 6)
            throw new IllegalArgumentException("Invalid mac.");
        byte[] macBytes = new byte[6];
        for (int i = 0; i < 6; i++)
            macBytes[i] = (byte) Integer.parseInt(parts[i], 16);

        TxPacket tx = TxPacket.start(out, CommandCode.GetNetConf);
        tx.write(macBytes);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        rx.readDataAndCheck();
    }

    @Override
    public List<TagRecord> getTagRecords(int addr, int count)
            throws IOException, Ztx965nException {
        TxPacket tx = TxPacket.start(out, CommandCode.GetTagRecords);
        tx.writeWord(addr);
        tx.writeByte(count);
        tx.end();

        RxPacket rx = RxPacket.start(in, tx);
        int nRecord = rx.readByte() & 0xFF;
        List<TagRecord> list = new ArrayList<TagRecord>();
        for (int i = 0; i < nRecord; i++) {
            TagRecord record = new TagRecord().parse(rx);
            list.add(record);
        }

        rx.readDataAndCheck();
        return list;
    }

    // Base config helper

    public BaseConfig setBeep(boolean enabled)
            throws IOException, Ztx965nException {
        BaseConfig config = getBaseConfig();
        if (config.beep != enabled) {
            config.beep = enabled;
            setBaseConfig(config);
        }
        return config;
    }

    // Active config helper

    public ActiveConfig setAntenna(int antenna)
            throws IOException, Ztx965nException {
        ActiveConfig config = getActiveConfig();
        if (config.antenna != antenna) {
            config.antenna = antenna;
            setActiveConfig(config);
        }
        return config;
    }

    public ActiveConfig setOutputFormat(int outputFormat)
            throws IOException, Ztx965nException {
        ActiveConfig config = getActiveConfig();
        if (config.outputFormat != outputFormat) {
            config.outputFormat = outputFormat;
            setActiveConfig(config);
        }
        return config;
    }

}
