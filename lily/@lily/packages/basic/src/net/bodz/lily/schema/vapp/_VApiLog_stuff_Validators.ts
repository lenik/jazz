import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class _VApiLog_stuff_Validators extends CoEntityValidators {

    constructor(type: _VApiLog_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApiLog_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateMessage(val: string) {
    }

    validateErr(val: string) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApiLog_stuff_Validators;
