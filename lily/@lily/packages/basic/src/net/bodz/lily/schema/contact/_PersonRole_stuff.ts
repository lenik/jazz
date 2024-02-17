
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { OrgUnit } from "./OrgUnit";
import type { Organization } from "./Organization";
import type { Person } from "./Person";
import type { _PersonRole_stuff_Type } from "./_PersonRole_stuff_Type";

export class _PersonRole_stuff extends CoEntity<Integer> {
    static TYPE = new _PersonRole_stuff_Type();

    id: int;
    role?: string;

    orgUnit?: OrgUnit;
    orgUnitId?: integer;

    person: Person;
    personId: int;

    org: Organization;
    orgId: int;

    constructor(o: any) {
        super(o);
    }
}
