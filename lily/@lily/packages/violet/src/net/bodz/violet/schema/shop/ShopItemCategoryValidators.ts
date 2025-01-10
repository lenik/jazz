import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
