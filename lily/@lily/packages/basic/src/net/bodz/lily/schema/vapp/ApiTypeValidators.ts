import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ApiTypeTypeInfo from "./ApiTypeTypeInfo";
import _ApiType_stuff_Validators from "./_ApiType_stuff_Validators";

export class ApiTypeValidators extends _ApiType_stuff_Validators {

    constructor(type: ApiTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ApiTypeTypeInfo;
    }

}

export default ApiTypeValidators;
