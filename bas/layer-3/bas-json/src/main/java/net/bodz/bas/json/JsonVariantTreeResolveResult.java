package net.bodz.bas.json;

import net.bodz.bas.fmt.json.JsonVariant;

public class JsonVariantTreeResolveResult {

    public int validCount;

    public JsonVariantTreeResolveStatus status = JsonVariantTreeResolveStatus.NONE;

    public JsonVariant stoppedAt;

}