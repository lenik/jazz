package net.bodz.bas.m2.activator;

import org.apache.maven.model.Profile;
import org.apache.maven.model.building.ModelProblemCollector;
import org.apache.maven.model.path.PathTranslator;
import org.apache.maven.model.profile.ProfileActivationContext;
import org.apache.maven.model.profile.activation.ProfileActivator;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Configuration;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;

@Component(role = ProfileActivator.class, hint = "model-property")
public class ModelPropertyProfileActivator
        implements ProfileActivator {

    @Requirement 
    private Logger logger;

    /**
     * Configured as part of the CustomActivator's setup of this ProfileActivator, before the
     * CustomActivator delegates the profile-activation process to it. This IS a required element,
     * and it can be reversed (negated) using a '!' prefix. Reversing the name means one of two
     * things: <br/>
     * <ul>
     * <li>If the value configuration is null, make sure the property doesn't exist in the lineage.</li>
     * <li>If the value configuration does exist, make sure the retrieved value doesn't match it.</li>
     * </ul>
     */
    @Configuration(name = "name", value = "")
    private String name;

    /**
     * Configured as part of the CustomActivator's setup of this ProfileActivator, before the
     * CustomActivator delegates the profile-activation process to it. This is NOT a required
     * element, and it can be reversed (negated) using a '!' prefix.
     */
    @Configuration(name = "value", value = "")
    private String value;

    @Requirement
    private PathTranslator pathTranslator;

// static Field inputSources;

    @Override
    public boolean isActive(Profile profile, ProfileActivationContext context, ModelProblemCollector problems) {
        return false;
    }

}
