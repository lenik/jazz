import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ShopItemTypeInfo from "./ShopItemTypeInfo";
import _ShopItem_stuff_Validators from "./_ShopItem_stuff_Validators";

export class ShopItemValidators extends _ShopItem_stuff_Validators {

    constructor(type: ShopItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopItemTypeInfo;
    }

    validateTotal(val: BigDecimal) {
    }

}

export default ShopItemValidators;
