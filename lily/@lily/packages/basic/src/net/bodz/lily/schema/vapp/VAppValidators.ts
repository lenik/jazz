import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type VAppTypeInfo from "./VAppTypeInfo";
import _VApp_stuff_Validators from "./_VApp_stuff_Validators";

export class VAppValidators extends _VApp_stuff_Validators {

    constructor(type: VAppTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VAppTypeInfo;
    }

}

export default VAppValidators;
