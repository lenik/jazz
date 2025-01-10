import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
