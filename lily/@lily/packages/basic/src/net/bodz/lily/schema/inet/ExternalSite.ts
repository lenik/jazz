import _ExternalSite_stuff from "./_ExternalSite_stuff";
import { _ExternalSite_stuff_Type } from "./_ExternalSite_stuff_Type";

export class ExternalSite extends _ExternalSite_stuff {
    static TYPE = new _ExternalSite_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ExternalSite;
