import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type CategoryDefTypeInfo from "./CategoryDefTypeInfo";
import _CategoryDef_stuff_Validators from "./_CategoryDef_stuff_Validators";

export class CategoryDefValidators extends _CategoryDef_stuff_Validators {

    constructor(type: CategoryDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CategoryDefTypeInfo;
    }

}

export default CategoryDefValidators;
