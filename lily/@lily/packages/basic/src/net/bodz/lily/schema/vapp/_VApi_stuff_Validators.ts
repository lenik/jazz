import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class _VApi_stuff_Validators extends CoEntityValidators {

    constructor(type: _VApi_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApi_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateCallback(val: string) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApi_stuff_Validators;
