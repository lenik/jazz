package net.bodz.bas.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.object.IReloadable;
import net.bodz.bas.t.order.PriorityComparator;

public class SmsProviders
        implements IReloadable, IShortMessageService {

    List<IShortMessageService> allImpls = new ArrayList<IShortMessageService>();

    public SmsProviders() {
        reload();
    }

    @Override
    public synchronized void reload()
            throws LoadException {
        allImpls.clear();
        for (ISmsProvider provider : ServiceLoader.load(ISmsProvider.class)) {
            List<IShortMessageService> impls = provider.getShortMessageServices();
            allImpls.addAll(impls);
        }
        Collections.sort(allImpls, PriorityComparator.INSTANCE);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        for (IShortMessageService impl : allImpls)
            impl.setAutoCommit(autoCommit);
    }

    @Override
    public void commit()
            throws IOException, ParseException {
        for (IShortMessageService impl : allImpls)
            impl.commit();
    }

    @Override
    public boolean sendText(String recipient, String text)
            throws IOException {
        for (IShortMessageService impl : allImpls)
            if (impl.sendText(recipient, text))
                return true;
        return false;
    }

    @Override
    public boolean sendPrepared(String recipient, String templateName, Object... params)
            throws IOException, ParseException {
        for (IShortMessageService impl : allImpls)
            if (impl.sendPrepared(recipient, templateName, params))
                return true;
        return false;
    }

    @Override
    public void addSmsListener(ISmsListener listener) {
        for (IShortMessageService impl : allImpls)
            impl.addSmsListener(listener);
    }

    @Override
    public void removeSmsListener(ISmsListener listener) {
        for (IShortMessageService impl : allImpls)
            impl.removeSmsListener(listener);
    }

    static SmsProviders instance = new SmsProviders();

    public static SmsProviders getInstance() {
        return instance;
    }

    public static IShortMessageService getSms() {
        return instance;
    }

}
