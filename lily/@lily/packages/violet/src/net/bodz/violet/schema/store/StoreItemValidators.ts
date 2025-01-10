import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
