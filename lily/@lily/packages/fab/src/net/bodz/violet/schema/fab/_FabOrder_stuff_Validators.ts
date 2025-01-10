import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import type Plan from "@lily/violet/src/net/bodz/violet/schema/plan/Plan";

import type _FabOrder_stuff_TypeInfo from "./_FabOrder_stuff_TypeInfo";

export class _FabOrder_stuff_Validators extends CoMessageValidators {

    constructor(type: _FabOrder_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabOrder_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateNitem(val: int) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateTaskCount(val: int) {
    }

    validateProcessCount(val: int) {
    }

    validateTrackCount(val: int) {
    }

    validateClerk(val: Person) {
    }

    validatePlan(val: Plan) {
    }

    validateCustomOrg(val: Organization) {
    }

    validateCustom(val: Person) {
    }

}

export default _FabOrder_stuff_Validators;
