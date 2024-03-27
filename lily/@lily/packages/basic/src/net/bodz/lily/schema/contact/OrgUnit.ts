import type { List } from "@skeljs/core/src/lang/basetype";

import Contact from "./Contact";
import OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff from "./_OrgUnit_stuff";

export class OrgUnit extends _OrgUnit_stuff {

    static _typeInfo: OrgUnitTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = OrgUnitTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    contact?: Contact
    staff?: List<PersonRole>

    constructor(o?: any) {
        super(o);
    }
}

export default OrgUnit;
