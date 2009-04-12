package net.bodz.dist.ins;

import java.util.EventObject;

public class ProgressChangeEvent extends EventObject {

    private static final long serialVersionUID = 8167262523409818913L;

    public float              p;

    public ProgressChangeEvent(Progress progress) {
        super(progress);
        p = progress.get();
    }

}
