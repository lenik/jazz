import ExternalSiteTypeInfo from "./ExternalSiteTypeInfo";
import _ExternalSite_stuff from "./_ExternalSite_stuff";

export class ExternalSite extends _ExternalSite_stuff {
    static _typeInfo: ExternalSiteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ExternalSiteTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ExternalSite;
