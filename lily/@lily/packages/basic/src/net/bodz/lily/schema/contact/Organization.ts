import OrganizationTypeInfo from "./OrganizationTypeInfo";
import _Organization_stuff from "./_Organization_stuff";

export class Organization extends _Organization_stuff {
    static _typeInfo: OrganizationTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new OrganizationTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Organization;
