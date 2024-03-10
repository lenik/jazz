import ExternalSiteTypeInfo from "./ExternalSiteTypeInfo";
import _ExternalSite_stuff from "./_ExternalSite_stuff";

export class ExternalSite extends _ExternalSite_stuff<ExternalSite> {

    static _typeInfo: ExternalSiteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ExternalSiteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ExternalSite;
