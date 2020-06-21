package net.bodz.bas.sms;

import java.io.IOException;
import java.util.*;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractSmsImpl
        implements IShortMessageService {

    boolean autoCommit = true;
    LinkedList<SmsRecord> records = new LinkedList<>();
    Map<String, SmsTemplate> templates = new HashMap<>();

    List<ISmsListener> listeners = new ArrayList<>();

    @Override
    public int getPriority() {
        return 0;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    protected abstract boolean canSend(SmsRecord record);

    @Override
    public boolean sendText(String recipient, String text)
            throws IOException {
        SmsRecord record = new SmsRecord(recipient, text);
        if (!canSend(record))
            return false;
        records.addLast(record);
        if (autoCommit)
            try {
                commit();
            } catch (ParseException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        return true;
    }

    public SmsTemplate getTemplate(String name) {
        return templates.get(name);
    }

    public void addTemplate(String name, Object id) {
        addTemplate(name, id, null);
    }

    public void addTemplate(String name, Object id, String content) {
        SmsTemplate template = new SmsTemplate(name, id, content);
        templates.put(name, template);
    }

    @Override
    public boolean sendPrepared(String recipient, String templateName, Object... parameters)
            throws IOException, ParseException {
        SmsRecord record = new SmsRecord(recipient, templateName, Arrays.asList(parameters));
        if (!canSend(record))
            return false;
        records.addLast(record);
        if (autoCommit)
            commit();
        return true;
    }

    @Override
    public synchronized void commit()
            throws IOException, ParseException {
        LinkedList<SmsRecord> dump;
        synchronized (this) {
            dump = records;
            records = new LinkedList<>();
        }
        send(dump);

        for (SmsRecord record : dump) {
            SmsSendState state = SmsSendState.IGNORED;
            if (record.response != null)
                state = record.response.getSendState();
            for (ISmsListener listener : listeners) {
                switch (state) {
                case SUCCESS:
                    listener.sent(record);
                    break;
                case FAILED:
                    listener.failed(record);
                    break;
                case IGNORED:
                    listener.ignored(record);
                    break;
                default:
                }
                listener.completed(record);
            }
        }
    }

    protected abstract void send(LinkedList<SmsRecord> records)
            throws IOException, ParseException;

    @Override
    public void addSmsListener(ISmsListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        listeners.add(listener);
    }

    @Override
    public void removeSmsListener(ISmsListener listener) {
        listeners.remove(listener);
    }

    protected static Map<String, List<SmsRecord>> divideByTemplate(List<SmsRecord> records, String nullKey) {
        Map<String, List<SmsRecord>> map = new LinkedHashMap<>();
        for (SmsRecord record : records) {
            String k = record.templateName;
            if (k == null)
                k = nullKey;
            List<SmsRecord> list = map.get(k);
            if (list == null)
                map.put(k, list = new ArrayList<>());
            list.add(record);
        }
        return map;
    }

}
