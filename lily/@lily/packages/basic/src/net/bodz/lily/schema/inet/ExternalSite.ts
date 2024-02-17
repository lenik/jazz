
import { * as validators } from "./PersonValidators";
import type { _ExternalSite_stuff } from "./_ExternalSite_stuff";

export class ExternalSite extends _ExternalSite_stuff {
    static TYPE = new ExternalSiteType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
