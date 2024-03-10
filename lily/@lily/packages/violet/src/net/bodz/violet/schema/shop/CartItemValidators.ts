import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type Artifact from "../art/Artifact";
import type CartItemTypeInfo from "./CartItemTypeInfo";
import _CartItem_stuff_Validators from "./_CartItem_stuff_Validators";

export class CartItemValidators extends _CartItem_stuff_Validators {

    constructor(type: CartItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CartItemTypeInfo;
    }

    validateAmount(val: BigDecimal) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default CartItemValidators;
