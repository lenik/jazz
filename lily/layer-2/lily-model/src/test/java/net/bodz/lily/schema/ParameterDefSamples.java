package net.bodz.lily.schema;

import net.bodz.lily.schema.ParameterDef;

public class ParameterDefSamples {

    public static ParameterDef build() {
        ParameterDef a = new ParameterDef();
        a.setLabel("parameterDef-1");
        a.setDescription("A parameterDef named parameterDef-1.");
        return a;
    }

}