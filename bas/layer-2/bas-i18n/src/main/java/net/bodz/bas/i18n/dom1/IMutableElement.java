package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.iString;

public interface IMutableElement
        extends IElement {

    void setName(String name);

    void setLabel(iString label);

    void setDescription(iString description);

    void setHelpDoc(iString helpDoc);

    void setDetailLevel(int verboseLevel);

    void setModifiers(int modifiers);

    // IMutablePriority
    void setPriority(int priority);

    class fn {

        public static void copy1(IElement src, IMutableElement dst) {
            dst.setName(src.getName());
            dst.setLabel(src.getLabel());
            dst.setDescription(src.getDescription());
            dst.setHelpDoc(src.getHelpDoc());
            dst.setDetailLevel(src.getDetailLevel());
            dst.setModifiers(src.getModifiers());
            dst.setPriority(src.getPriority());
        }

    }

}
