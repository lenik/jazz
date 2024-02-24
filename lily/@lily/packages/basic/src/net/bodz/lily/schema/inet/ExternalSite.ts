import ExternalSiteTypeInfo from "./ExternalSiteTypeInfo";
import _ExternalSite_stuff from "./_ExternalSite_stuff";

export class ExternalSite extends _ExternalSite_stuff {
    static TYPE = new ExternalSiteTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ExternalSite;
