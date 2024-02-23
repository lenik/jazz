import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import OrgUnit from "./OrgUnit";
import Organization from "./Organization";
import Person from "./Person";
import _PersonRole_stuff_Type from "./_PersonRole_stuff_Type";

export class _PersonRole_stuff extends CoEntity<integer> {
    static TYPE = new _PersonRole_stuff_Type();

    id: integer;
    role?: string;

    orgUnit?: OrgUnit;
    orgUnitId?: integer;

    person: Person;
    personId: integer;

    org: Organization;
    orgId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonRole_stuff;
