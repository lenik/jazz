import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabProcessTypeInfo from "./FabProcessTypeInfo";
import _FabProcess_stuff_Validators from "./_FabProcess_stuff_Validators";

export class FabProcessValidators extends _FabProcess_stuff_Validators {

    constructor(type: FabProcessTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabProcessTypeInfo;
    }

}

export default FabProcessValidators;
