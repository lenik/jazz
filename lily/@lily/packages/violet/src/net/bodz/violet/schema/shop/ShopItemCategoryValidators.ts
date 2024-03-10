import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ShopItemCategoryTypeInfo from "./ShopItemCategoryTypeInfo";
import _ShopItemCategory_stuff_Validators from "./_ShopItemCategory_stuff_Validators";

export class ShopItemCategoryValidators extends _ShopItemCategory_stuff_Validators {

    constructor(type: ShopItemCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopItemCategoryTypeInfo;
    }

}

export default ShopItemCategoryValidators;
