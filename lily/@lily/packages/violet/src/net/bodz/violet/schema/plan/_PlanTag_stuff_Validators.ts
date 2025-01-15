import { ValidateResult } from "skel01-core/src/ui/types";
import CoTagValidators from "lily-basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _PlanTag_stuff_TypeInfo from "./_PlanTag_stuff_TypeInfo";

export class _PlanTag_stuff_Validators extends CoTagValidators {

    constructor(type: _PlanTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanTag_stuff_TypeInfo;
    }

}

export default _PlanTag_stuff_Validators;
