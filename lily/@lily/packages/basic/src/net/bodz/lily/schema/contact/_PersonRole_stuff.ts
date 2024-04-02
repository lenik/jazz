import type { int } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import type Person from "./Person";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class _PersonRole_stuff extends IdEntity<int> {

    static _typeInfo: _PersonRole_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PersonRole_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    role?: string;

    orgUnit?: OrgUnit;
    orgUnitId?: int;

    person: Person;
    personId: int;

    org: Organization;
    orgId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonRole_stuff;
