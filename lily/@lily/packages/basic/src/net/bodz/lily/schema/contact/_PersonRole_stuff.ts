import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import type Person from "./Person";
import _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class _PersonRole_stuff extends CoEntity<integer> {
    static TYPE = new _PersonRole_stuff_TypeInfo();

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
