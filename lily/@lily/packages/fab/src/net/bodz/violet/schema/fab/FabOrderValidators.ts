import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type FabOrderTypeInfo from "./FabOrderTypeInfo";
import _FabOrder_stuff_Validators from "./_FabOrder_stuff_Validators";

export class FabOrderValidators extends _FabOrder_stuff_Validators {

    constructor(type: FabOrderTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabOrderTypeInfo;
    }

}

export default FabOrderValidators;
