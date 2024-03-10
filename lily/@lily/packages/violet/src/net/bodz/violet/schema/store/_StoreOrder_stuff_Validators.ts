import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import type FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import type Plan from "../plan/Plan";
import type StoreCategory from "./StoreCategory";
import type StoreOrder from "./StoreOrder";
import type StorePhase from "./StorePhase";
import type _StoreOrder_stuff_TypeInfo from "./_StoreOrder_stuff_TypeInfo";

export class _StoreOrder_stuff_Validators extends CoEntityValidators {

    constructor(type: _StoreOrder_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _StoreOrder_stuff_TypeInfo;
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

    validateLength(val: int) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validatePerson(val: Person) {
    }

    validatePlan(val: Plan) {
    }

    validatePhase(val: StorePhase) {
    }

    validateCategory(val: StoreCategory) {
    }

    validatePrev(val: StoreOrder) {
    }

    validateOp(val: User) {
    }

    validateForm(val: FormDef) {
    }

    validateOrg(val: Organization) {
    }

    validateOrgUnit(val: OrgUnit) {
    }

}

export default _StoreOrder_stuff_Validators;
