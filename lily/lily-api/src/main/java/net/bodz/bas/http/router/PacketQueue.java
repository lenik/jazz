package net.bodz.bas.http.router;

import java.util.LinkedList;

import net.bodz.bas.fmt.json.IJsonForm;

public class PacketQueue<T extends IJsonForm>
        extends LinkedList<T> {

    private static final long serialVersionUID = 1L;

}
