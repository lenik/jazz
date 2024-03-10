import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type ShopItem from "./ShopItem";
import type _CartItem_stuff_TypeInfo from "./_CartItem_stuff_TypeInfo";

export class _CartItem_stuff_Validators extends CoEntityValidators {

    constructor(type: _CartItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CartItem_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validatePrice(val: BigDecimal) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateShopItem(val: ShopItem) {
    }

}

export default _CartItem_stuff_Validators;
