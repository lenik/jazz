import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabOrderItemTypeInfo from "./FabOrderItemTypeInfo";
import _FabOrderItem_stuff_Validators from "./_FabOrderItem_stuff_Validators";

export class FabOrderItemValidators extends _FabOrderItem_stuff_Validators {

    constructor(type: FabOrderItemTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabOrderItemTypeInfo;
    }

}

export default FabOrderItemValidators;
