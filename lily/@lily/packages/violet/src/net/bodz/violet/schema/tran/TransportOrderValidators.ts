import type { List, int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
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

    validateArrivedDate(val: OffsetDateTime) {
    }

    validateItems(val: List<TransportOrderItem>) {
    }

    validateLength(val: int) {
    }

    validateShipDate(val: OffsetDateTime) {
    }

}

export default TransportOrderValidators;
