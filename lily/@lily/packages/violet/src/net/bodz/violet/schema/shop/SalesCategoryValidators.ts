import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type SalesCategoryTypeInfo from "./SalesCategoryTypeInfo";
import _SalesCategory_stuff_Validators from "./_SalesCategory_stuff_Validators";

export class SalesCategoryValidators extends _SalesCategory_stuff_Validators {

    constructor(type: SalesCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SalesCategoryTypeInfo;
    }

}

export default SalesCategoryValidators;
