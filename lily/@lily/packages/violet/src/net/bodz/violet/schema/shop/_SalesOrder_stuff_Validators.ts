import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type Plan from "../plan/Plan";
import type SalesCategory from "./SalesCategory";
import type SalesOrder from "./SalesOrder";
import type SalesPhase from "./SalesPhase";
import type _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class _SalesOrder_stuff_Validators extends CoEntityValidators {

    constructor(type: _SalesOrder_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _SalesOrder_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateYear(val: int) {
    }

    validateSubject(val: string) {
    }

    validateRawText(val: string) {
    }

    validateFormArguments(val: string) {
    }

    validateProperties(val: JsonVariant) {
    }

    validateLength(val: int) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateCustomer(val: Person) {
    }

    validateForm(val: FormDef) {
    }

    validatePhase(val: SalesPhase) {
    }

    validateCustomerOrg(val: Organization) {
    }

    validatePreviousOrder(val: SalesOrder) {
    }

    validateOp(val: User) {
    }

    validatePlan(val: Plan) {
    }

    validateCategory(val: SalesCategory) {
    }

}

export default _SalesOrder_stuff_Validators;
