import type { integer } from "@skeljs/core/src/lang/type";

import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import Party from "./Party";
import _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class _OrgUnit_stuff extends Party {
    static TYPE = new _OrgUnit_stuff_TypeInfo();

    properties?: any;
    depth: integer;

    org: Organization;
    orgId: integer;

    parent?: OrgUnit;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _OrgUnit_stuff;
