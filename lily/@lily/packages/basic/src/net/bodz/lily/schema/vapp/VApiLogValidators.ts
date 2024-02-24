import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

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
