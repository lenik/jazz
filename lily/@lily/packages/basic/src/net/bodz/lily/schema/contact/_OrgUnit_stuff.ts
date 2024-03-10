import type { int } from "@skeljs/core/src/lang/basetype";

import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import Party from "./Party";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class _OrgUnit_stuff extends Party {

    static _typeInfo: _OrgUnit_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _OrgUnit_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    depth: int;

    org: Organization;
    orgId: int;

    parent?: OrgUnit;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _OrgUnit_stuff;
