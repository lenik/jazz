import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type VApiLogTypeInfo from "./VApiLogTypeInfo";
import _VApiLog_stuff_Validators from "./_VApiLog_stuff_Validators";

export class VApiLogValidators extends _VApiLog_stuff_Validators {

    constructor(type: VApiLogTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VApiLogTypeInfo;
    }

}

export default VApiLogValidators;
