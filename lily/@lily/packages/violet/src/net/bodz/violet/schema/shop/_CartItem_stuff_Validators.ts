import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type ShopItem from "./ShopItem";
import type _CartItem_stuff_TypeInfo from "./_CartItem_stuff_TypeInfo";

export class _CartItem_stuff_Validators extends IdEntityValidators {

    constructor(type: _CartItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CartItem_stuff_TypeInfo;
    }

    validatePrice(val: BigDecimal) {
    }

    validateQuantity(val: BigDecimal) {
    }

    validateShopItem(val: ShopItem) {
    }

}

export default _CartItem_stuff_Validators;
