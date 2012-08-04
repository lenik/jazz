package net.bodz.swt.draw.core.particle;

import java.util.EventListener;

public interface ParticlePaintListener
        extends EventListener {

    void paint(ParticlePaintEvent e);

}
