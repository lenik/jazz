import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type VApiTypeInfo from "./VApiTypeInfo";
import _VApi_stuff_Validators from "./_VApi_stuff_Validators";

export class VApiValidators extends _VApi_stuff_Validators {

    constructor(type: VApiTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VApiTypeInfo;
    }

}

export default VApiValidators;
