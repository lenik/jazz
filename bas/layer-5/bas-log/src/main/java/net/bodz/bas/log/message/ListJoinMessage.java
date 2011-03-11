package net.bodz.bas.log.message;

import java.util.ArrayList;
import java.util.List;

public class ListJoinMessage
        implements IMessage {

    private static final long serialVersionUID = 1L;

    private final List<Object> pieceList;

    public ListJoinMessage() {
        this.pieceList = new ArrayList<Object>();
    }

    public ListJoinMessage(Object[] messagePieces) {
        if (messagePieces == null)
            throw new NullPointerException("messagePieces");
        this.pieceList = new ArrayList<Object>(messagePieces.length);
        for (int i = 0; i < messagePieces.length; i++)
            this.pieceList.add(messagePieces[i]);
    }

    @Override
    public IMessage add(Object messagePiece) {
        pieceList.add(messagePiece);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(pieceList.size() * 16);
        for (Object piece : pieceList)
            buf.append(piece);
        return buf.toString();
    }

}
