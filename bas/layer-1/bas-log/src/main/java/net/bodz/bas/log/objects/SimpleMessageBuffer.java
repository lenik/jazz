package net.bodz.bas.log.objects;

import java.io.Serializable;

public class SimpleMessageBuffer
        implements IMessage, Serializable {

    private static final long serialVersionUID = 1L;

    private Object simpleMessage;

    public SimpleMessageBuffer(Object simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    @Override
    public IMessage add(Object messagePiece) {
        IMessage ext = new ListJoinMessage();
        ext = ext.add(this.simpleMessage);
        ext = ext.add(messagePiece);
        return ext;
    }

    @Override
    public String toString() {
        return String.valueOf(simpleMessage);
    }

}
