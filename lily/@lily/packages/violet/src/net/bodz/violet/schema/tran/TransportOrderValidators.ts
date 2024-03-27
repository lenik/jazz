import type { List, int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TransportOrderItem from "./TransportOrderItem";
import type TransportOrderTypeInfo from "./TransportOrderTypeInfo";
import _TransportOrder_stuff_Validators from "./_TransportOrder_stuff_Validators";

export class TransportOrderValidators extends _TransportOrder_stuff_Validators {

    constructor(type: TransportOrderTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TransportOrderTypeInfo;
    }

    validateArrivedDate(val: ZonedDateTime) {
    }

    validateItems(val: List<TransportOrderItem>) {
    }

    validateLength(val: int) {
    }

    validateShipDate(val: ZonedDateTime) {
    }

}

export default TransportOrderValidators;