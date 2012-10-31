package net.bodz.bas.log.message;

public class ArrayJoinMessage
        implements IMessage {

    private static final long serialVersionUID = 1L;

    private Object[] messagePieces;
    private String joined;

    public ArrayJoinMessage(Object[] messagePieces) {
        if (messagePieces == null)
            throw new NullPointerException("messagePieces");
        this.messagePieces = messagePieces;
    }

    @Override
    public IMessage add(Object messagePiece) {
        if (joined == null) {
            ListJoinMessage listbuf = new ListJoinMessage(messagePieces);
            return listbuf.add(messagePiece);
        } else {
            IMessage ext = new ListJoinMessage();
            ext.add(joined);
            ext.add(messagePiece);
            return ext;
        }
    }

    @Override
    public String toString() {
        if (joined == null) {
            StringBuilder buf = new StringBuilder(messagePieces.length * 16);
            for (int i = 0; i < messagePieces.length; i++)
                buf.append(messagePieces[i]);
            joined = buf.toString();
            messagePieces = null;
        }
        return joined;
    }

}
