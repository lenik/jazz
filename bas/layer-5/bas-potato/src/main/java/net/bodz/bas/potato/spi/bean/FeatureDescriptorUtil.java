package net.bodz.bas.potato.spi.bean;

import java.beans.FeatureDescriptor;

import net.bodz.bas.potato.traits.IElement;

public class FeatureDescriptorUtil {

    public static int getFeaturePreferenceLevel(FeatureDescriptor feature) {
        int pref = 1; // normal
        if (feature.isPreferred())
            pref--;
        if (feature.isExpert())
            pref++;
        if (feature.isHidden())
            pref = -pref;
        return pref;
    }

    public static void applyPreferenceLevelToFeature(FeatureDescriptor feature, int pref) {
        switch (pref) {
        case 0:
            feature.setPreferred(true);
            break;
        case 1:
            break;
        default:
            if (pref < 0) {
                feature.setHidden(true);
                pref = -pref;
            }
            if (pref >= 2)
                feature.setExpert(true);
        }
    }

    public static void initFeatureDescriptorFromPotatoElement(FeatureDescriptor featureDescriptor,
            IElement potatoElement) {

        featureDescriptor.setName(potatoElement.getName());
        featureDescriptor.setDisplayName(potatoElement.getDisplayName().toString());
        featureDescriptor.setShortDescription(potatoElement.getDescription().toString());

        int pref = potatoElement.getPreferenceLevel();
        applyPreferenceLevelToFeature(featureDescriptor, pref);
    }

}
