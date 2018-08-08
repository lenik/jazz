package net.bodz.lily.api;

public class ApiSamples {

    public static Api build() {
        Api a = new Api();
        a.setLabel("api-1");
        a.setDescription("A api named api-1.");
        a.setUom("times");
        return a;
    }

}
