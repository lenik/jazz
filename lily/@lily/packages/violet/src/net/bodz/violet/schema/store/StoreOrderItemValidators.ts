import type { BigDecimal, long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type StoreOrderItemTypeInfo from "./StoreOrderItemTypeInfo";
import _StoreOrderItem_stuff_Validators from "./_StoreOrderItem_stuff_Validators";

export class StoreOrderItemValidators extends _StoreOrderItem_stuff_Validators {

    constructor(type: StoreOrderItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StoreOrderItemTypeInfo;
    }

    validateAmount(val: BigDecimal) {
    }

}

export default StoreOrderItemValidators;
