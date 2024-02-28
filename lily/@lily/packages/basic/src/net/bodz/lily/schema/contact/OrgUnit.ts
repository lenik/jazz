import List from "../../../../../java/util/List";
import Contact from "./Contact";
import OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff from "./_OrgUnit_stuff";

export class OrgUnit extends _OrgUnit_stuff {
    static _typeInfo: OrgUnitTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new OrgUnitTypeInfo();
        return this._typeInfo;
    }


    contact?: Contact
    staff?: List<PersonRole>

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default OrgUnit;
