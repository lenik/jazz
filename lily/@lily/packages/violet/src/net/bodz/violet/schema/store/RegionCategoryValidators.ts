import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type RegionCategoryTypeInfo from "./RegionCategoryTypeInfo";
import _RegionCategory_stuff_Validators from "./_RegionCategory_stuff_Validators";

export class RegionCategoryValidators extends _RegionCategory_stuff_Validators {

    constructor(type: RegionCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as RegionCategoryTypeInfo;
    }

}

export default RegionCategoryValidators;
