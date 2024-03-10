import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ShopCategoryTypeInfo from "./ShopCategoryTypeInfo";
import _ShopCategory_stuff_Validators from "./_ShopCategory_stuff_Validators";

export class ShopCategoryValidators extends _ShopCategory_stuff_Validators {

    constructor(type: ShopCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopCategoryTypeInfo;
    }

}

export default ShopCategoryValidators;
