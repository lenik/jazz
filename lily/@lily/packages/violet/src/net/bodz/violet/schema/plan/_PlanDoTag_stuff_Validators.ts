import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTagValidators from "@lily/basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _PlanDoTag_stuff_TypeInfo from "./_PlanDoTag_stuff_TypeInfo";

export class _PlanDoTag_stuff_Validators extends CoTagValidators {

    constructor(type: _PlanDoTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanDoTag_stuff_TypeInfo;
    }

}

export default _PlanDoTag_stuff_Validators;
