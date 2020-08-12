package net.bodz.bas.potato.provider.bean;

import com.googlecode.openbeans.FeatureDescriptor;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.IPotatoElement;

public class FeatureDescriptorUtil {

    public static int getDetailLevel(FeatureDescriptor descriptor) {
        int detail = DetailLevel.NORMAL;

        if (descriptor.isPreferred())
            detail = DetailLevel.CRITICAL;
        else if (descriptor.isHidden())
            detail = DetailLevel.HIDDEN;
        else if (descriptor.isExpert())
            detail = DetailLevel.EXPERT;

        return detail;
    }

    public static void setDetailLevel(FeatureDescriptor descriptor, int detail) {
        descriptor.setPreferred(detail <= DetailLevel.CRITICAL);
        descriptor.setExpert(detail >= DetailLevel.EXPERT && detail < DetailLevel.HIDDEN);
        descriptor.setHidden(detail >= DetailLevel.HIDDEN);
    }

    public static void initFeatureDescriptorFromPotatoElement(FeatureDescriptor descriptor, IPotatoElement element) {
        descriptor.setName(element.getName());
        descriptor.setDisplayName(element.getLabel().toString());
        descriptor.setShortDescription(element.getDescription().toString());

        int detailLevel = element.getDetailLevel();
        setDetailLevel(descriptor, detailLevel);
    }

}
