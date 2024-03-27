import type { BigDecimal } from "@skeljs/core/src/lang/basetype";

import Artifact from "../art/Artifact";
import CartItemTypeInfo from "./CartItemTypeInfo";
import _CartItem_stuff from "./_CartItem_stuff";

export class CartItem extends _CartItem_stuff {

    static _typeInfo: CartItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CartItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    amount?: BigDecimal
    artifact?: Artifact

    constructor(o?: any) {
        super(o);
    }
}

export default CartItem;
