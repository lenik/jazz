package net.bodz.lily.security.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.sms.IShortMessageService;
import net.bodz.sms.ISmsProvider;
import net.bodz.sms.SmsProviders;
import net.bodz.sms.ucpaas.DefaultUcpaasClient;
import net.bodz.sms.ucpaas.UcpaasId;
import net.bodz.sms.ucpaas.UcpaasSmsImpl;

public class TestSmsProvider
        implements ISmsProvider {

    UcpaasSmsImpl impl;

    static final UcpaasId lenik;
    static {
        lenik = new UcpaasId();
        lenik.appid = "2cfbd61bf2b2460ab0087304fb2b0b64";
        lenik.sid = "890c213aeb898a7490b3b14db98b63b5";
        lenik.token = "aa3fd35c9ff72bec5d457ea4ae12adb4";
    }

    public TestSmsProvider() {
        DefaultUcpaasClient client = DefaultUcpaasClient.getInstance();
        impl = new UcpaasSmsImpl(client, lenik);
    }

    @Override
    public List<IShortMessageService> getShortMessageServices() {
        return Arrays.<IShortMessageService> asList(impl);
    }

    public static void main(String[] args)
            throws IOException, ParseException {
        IShortMessageService sms = SmsProviders.getSms();
        String template = "544801";
        String param = "1234";
        sms.sendPrepared("13819471680", template, param);
    }

}
