import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
