package net.bodz.lily.pam;

import java.util.Map;

public interface IPamModule {

    Map<String, PamField> getPamFields();

    PamField getPamField(String name);

    void verify(PamStatement statement)
            throws PamValidateException;

}
