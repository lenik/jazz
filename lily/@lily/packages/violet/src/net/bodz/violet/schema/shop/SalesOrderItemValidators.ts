import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "skel01-core/src/ui/types";

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

    validateBeginTime(val: OffsetDateTime) {
    }

    validateDeadline(val: OffsetDateTime) {
    }

    validateEndTime(val: OffsetDateTime) {
    }

    validateOrderTime(val: OffsetDateTime) {
    }

}

export default SalesOrderItemValidators;
