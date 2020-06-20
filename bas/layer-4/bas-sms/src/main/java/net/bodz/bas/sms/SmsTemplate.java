package net.bodz.bas.sms;

public class SmsTemplate {

    public String name;
    public Object id;
    public String content;

    public SmsTemplate(String name, Object id, String content) {
        this.name = name;
        this.id = id;
        this.content = content;
    }

}
