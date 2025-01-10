import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type StoreCategoryTypeInfo from "./StoreCategoryTypeInfo";
import _StoreCategory_stuff_Validators from "./_StoreCategory_stuff_Validators";

export class StoreCategoryValidators extends _StoreCategory_stuff_Validators {

    constructor(type: StoreCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StoreCategoryTypeInfo;
    }

}

export default StoreCategoryValidators;
