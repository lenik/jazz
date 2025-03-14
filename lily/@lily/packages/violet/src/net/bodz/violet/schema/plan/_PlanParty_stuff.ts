import type { int, long } from "skel01-core/src/lang/basetype";
import IdEntity from "lily-basic/src/net/bodz/lily/concrete/IdEntity";
import type Organization from "lily-basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "lily-basic/src/net/bodz/lily/schema/contact/Person";

import type Plan from "./Plan";
import _PlanParty_stuff_TypeInfo from "./_PlanParty_stuff_TypeInfo";

export class _PlanParty_stuff extends IdEntity<long> {

    static _typeInfo: _PlanParty_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PlanParty_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    person?: Person;
    personId?: int;

    plan: Plan;
    planId: long;

    org?: Organization;
    orgId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanParty_stuff;
