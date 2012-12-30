package net.bodz.bas.potato.provider.bean;

import java.beans.FeatureDescriptor;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.potato.element.IPotatoElement;

public class FeatureDescriptorUtil {

    public static int getVerboseLevel(FeatureDescriptor descriptor) {
        int verboseLevel = IElement.PUBLIC_LEVEL;

        if (descriptor.isPreferred())
            verboseLevel = IElement.PREFERRED_LEVEL;
        else if (descriptor.isExpert())
            verboseLevel = IElement.EXPERT_LEVEL;
        else if (descriptor.isHidden())
            verboseLevel = IElement.HIDDEN_LEVEL;

        return verboseLevel;
    }

    public static void setVerboseLevel(FeatureDescriptor descriptor, int verboseLevel) {
        descriptor.setPreferred(verboseLevel <= IElement.PREFERRED_LEVEL);
        descriptor.setExpert(verboseLevel <= IElement.EXPERT_LEVEL);
        descriptor.setHidden(verboseLevel <= 0);
    }

    public static void initFeatureDescriptorFromPotatoElement(FeatureDescriptor descriptor, IPotatoElement element) {

        descriptor.setName(element.getName());
        descriptor.setLabel(element.getLabel().toString());
        descriptor.setShortDescription(element.getDescription().toString());

        int verboseLevel = element.getVerboseLevel();
        setVerboseLevel(descriptor, verboseLevel);
    }

}
