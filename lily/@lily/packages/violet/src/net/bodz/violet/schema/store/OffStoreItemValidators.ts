import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type OffStoreItemTypeInfo from "./OffStoreItemTypeInfo";
import _OffStoreItem_stuff_Validators from "./_OffStoreItem_stuff_Validators";

export class OffStoreItemValidators extends _OffStoreItem_stuff_Validators {

    constructor(type: OffStoreItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as OffStoreItemTypeInfo;
    }

}

export default OffStoreItemValidators;
