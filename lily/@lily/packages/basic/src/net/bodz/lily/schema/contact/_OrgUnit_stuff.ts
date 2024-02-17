
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { OrgUnit } from "./OrgUnit";
import type { Organization } from "./Organization";
import type { Party } from "./Party";
import type { _OrgUnit_stuff_Type } from "./_OrgUnit_stuff_Type";

export class _OrgUnit_stuff extends Party {
    static TYPE = new _OrgUnit_stuff_Type();

    depth: int;

    org: Organization;
    orgId: int;

    parent?: OrgUnit;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
