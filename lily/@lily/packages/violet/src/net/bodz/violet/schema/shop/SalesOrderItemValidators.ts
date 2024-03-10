import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type SalesOrderItemTypeInfo from "./SalesOrderItemTypeInfo";
import _SalesOrderItem_stuff_Validators from "./_SalesOrderItem_stuff_Validators";

export class SalesOrderItemValidators extends _SalesOrderItem_stuff_Validators {

    constructor(type: SalesOrderItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SalesOrderItemTypeInfo;
    }

    validateAmount(val: BigDecimal) {
    }

    validateBeginTime(val: ZonedDateTime) {
    }

    validateDeadline(val: ZonedDateTime) {
    }

    validateEndTime(val: ZonedDateTime) {
    }

    validateOrderTime(val: ZonedDateTime) {
    }

}

export default SalesOrderItemValidators;
