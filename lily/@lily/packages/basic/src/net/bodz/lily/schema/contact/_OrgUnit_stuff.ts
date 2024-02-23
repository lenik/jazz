import type { integer } from "@skeljs/core/src/lang/type";

import OrgUnit from "./OrgUnit";
import Organization from "./Organization";
import Party from "./Party";
import _OrgUnit_stuff_Type from "./_OrgUnit_stuff_Type";

export class _OrgUnit_stuff extends Party {
    static TYPE = new _OrgUnit_stuff_Type();

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
