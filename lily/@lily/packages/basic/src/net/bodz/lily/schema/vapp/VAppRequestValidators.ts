import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type VAppRequestTypeInfo from "./VAppRequestTypeInfo";
import _VAppRequest_stuff_Validators from "./_VAppRequest_stuff_Validators";

export class VAppRequestValidators extends _VAppRequest_stuff_Validators {

    constructor(type: VAppRequestTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VAppRequestTypeInfo;
    }

}

export default VAppRequestValidators;
