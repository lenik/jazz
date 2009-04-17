package net.bodz.swt.gui.pfl;

import java.util.EventObject;

public class LocationChangeEvent extends EventObject {

    private static final long serialVersionUID = 4990351296447595259L;

    public String             prev;
    public String             next;
    public int                reason;

    public LocationChangeEvent(Location loc, String prev, String next,
            int reason) {
        super(loc);
        this.prev = prev;
        this.next = next;
        this.reason = reason;
    }

    @Override
    public Location getSource() {
        return (Location) super.getSource();
    }

}
