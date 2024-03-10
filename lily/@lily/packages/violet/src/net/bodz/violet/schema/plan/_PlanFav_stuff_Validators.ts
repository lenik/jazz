import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type Plan from "./Plan";
import type _PlanFav_stuff_TypeInfo from "./_PlanFav_stuff_TypeInfo";

export class _PlanFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _PlanFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PlanFav_stuff_TypeInfo;
    }

    validatePlan(val: Plan) {
    }

}

export default _PlanFav_stuff_Validators;
