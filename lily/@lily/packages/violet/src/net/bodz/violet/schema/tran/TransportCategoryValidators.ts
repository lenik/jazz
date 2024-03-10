import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TransportCategoryTypeInfo from "./TransportCategoryTypeInfo";
import _TransportCategory_stuff_Validators from "./_TransportCategory_stuff_Validators";

export class TransportCategoryValidators extends _TransportCategory_stuff_Validators {

    constructor(type: TransportCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TransportCategoryTypeInfo;
    }

}

export default TransportCategoryValidators;
