import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "lily-basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type Organization from "lily-basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "lily-basic/src/net/bodz/lily/schema/contact/Person";

import type Plan from "./Plan";
import type _PlanParty_stuff_TypeInfo from "./_PlanParty_stuff_TypeInfo";

export class _PlanParty_stuff_Validators extends IdEntityValidators {

    constructor(type: _PlanParty_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanParty_stuff_TypeInfo;
    }

    validatePerson(val: Person) {
    }

    validatePlan(val: Plan) {
    }

    validateOrg(val: Organization) {
    }

}

export default _PlanParty_stuff_Validators;
