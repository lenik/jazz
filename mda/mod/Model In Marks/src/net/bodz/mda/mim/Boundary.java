package net.bodz.mda.mim;

import com.sun.tools.javac.util.Position;

public interface Boundary {

    BoundaryType getBoundaryType();

    Position getPosition();

    String getBoundaryStart();

    String getBoundaryEnd();

}
