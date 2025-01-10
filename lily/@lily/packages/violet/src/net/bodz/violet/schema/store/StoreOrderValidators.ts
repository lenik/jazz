import type { BigDecimal, List, int, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type StoreOrderItem from "./StoreOrderItem";
import type StoreOrderTypeInfo from "./StoreOrderTypeInfo";
import _StoreOrder_stuff_Validators from "./_StoreOrder_stuff_Validators";

export class StoreOrderValidators extends _StoreOrder_stuff_Validators {

    constructor(type: StoreOrderTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StoreOrderTypeInfo;
    }

    validateItems(val: List<StoreOrderItem>) {
    }

    validateLength(val: int) {
    }

    validateTotalAmount(val: BigDecimal) {
    }

    validateTotalQuantity(val: BigDecimal) {
    }

}

export default StoreOrderValidators;
