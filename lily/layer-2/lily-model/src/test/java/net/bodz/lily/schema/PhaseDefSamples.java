package net.bodz.lily.schema;

import net.bodz.lily.schema.PhaseDef;

public class PhaseDefSamples {

    public static PhaseDef build() {
        PhaseDef a = new PhaseDef();
        a.setLabel("phaseDef-1");
        a.setDescription("A phaseDef named phaseDef-1.");
        return a;
    }

}