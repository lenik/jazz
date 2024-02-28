import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type VAppRequestApiTypeInfo from "./VAppRequestApiTypeInfo";
import _VAppRequestApi_stuff_Validators from "./_VAppRequestApi_stuff_Validators";

export class VAppRequestApiValidators extends _VAppRequestApi_stuff_Validators {

    constructor(type: VAppRequestApiTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VAppRequestApiTypeInfo;
    }

}

export default VAppRequestApiValidators;
