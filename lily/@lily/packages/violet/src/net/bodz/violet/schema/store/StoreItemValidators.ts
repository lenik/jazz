import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type StoreItemTypeInfo from "./StoreItemTypeInfo";
import _StoreItem_stuff_Validators from "./_StoreItem_stuff_Validators";

export class StoreItemValidators extends _StoreItem_stuff_Validators {

    constructor(type: StoreItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StoreItemTypeInfo;
    }

}

export default StoreItemValidators;
