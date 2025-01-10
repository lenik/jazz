import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type Plan from "../plan/Plan";
import type SalesCategory from "./SalesCategory";
import type SalesOrder from "./SalesOrder";
import type SalesPhase from "./SalesPhase";
import type _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class _SalesOrder_stuff_Validators extends CoMessageValidators {

    constructor(type: _SalesOrder_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SalesOrder_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateLength(val: int) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateCustomer(val: Person) {
    }

    validatePhase(val: SalesPhase) {
    }

    validateCustomerOrg(val: Organization) {
    }

    validatePreviousOrder(val: SalesOrder) {
    }

    validatePlan(val: Plan) {
    }

    validateCategory(val: SalesCategory) {
    }

}

export default _SalesOrder_stuff_Validators;
