package net.bodz.bas.log.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StringFormatMessage
        implements IMessage {

    private static final long serialVersionUID = 1L;

    private transient final String format;
    private transient Object[] args; // non-final, to be freed later.

    private String result;

    public StringFormatMessage(String format, Object[] args) {
        if (format == null)
            throw new NullPointerException("format");
        this.format = format;
        this.args = args;
    }

    @Override
    public IMessage add(Object messagePiece) {
        IMessage ext = new ListJoinMessage();
        ext = ext.add(toString());
        ext = ext.add(messagePiece);
        return ext;
    }

    @Override
    public String toString() {
        if (result == null) {
            result = String.format(format, args);
            args = null;
        }
        return result;
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        toString();
        out.writeObject(result);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        result = (String) in.readObject();
    }

}
