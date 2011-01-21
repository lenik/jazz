package net.bodz.bas.m2.activator;

import java.util.Properties;

import org.apache.maven.model.Activation;
import org.apache.maven.model.Profile;
import org.apache.maven.model.profile.activation.AbstractProfileActivatorTest;

public class ModelPropertyProfileActivatorTest
        extends AbstractProfileActivatorTest<ModelPropertyProfileActivator> {

    public ModelPropertyProfileActivatorTest() {
        super(ModelPropertyProfileActivator.class);
    }

    Profile newProfile() {
        Activation activation = new Activation();

        Profile profile = new Profile();
        profile.setActivation(activation);

        return profile;
    }

    Properties newProperties() {
        Properties props = new Properties();
        return props;
    }

// public void testDefault() {
// Profile profile = newProfile();
// ProfileActivationContext context = newContext(newProperties(), null);
// assertActivation(true, profile, context);
// }

    public void testDummy() {
    }

}
