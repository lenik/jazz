import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanPartyTypeInfo from "./PlanPartyTypeInfo";
import _PlanParty_stuff_Validators from "./_PlanParty_stuff_Validators";

export class PlanPartyValidators extends _PlanParty_stuff_Validators {

    constructor(type: PlanPartyTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanPartyTypeInfo;
    }

}

export default PlanPartyValidators;
