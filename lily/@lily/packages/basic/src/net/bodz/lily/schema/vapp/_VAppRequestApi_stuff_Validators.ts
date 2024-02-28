import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ApiType from "./ApiType";
import type VAppRequest from "./VAppRequest";
import type _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class _VAppRequestApi_stuff_Validators extends CoEntityValidators {

    constructor(type: _VAppRequestApi_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppRequestApi_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateParent(val: VAppRequest) {
    }

    validateApi(val: ApiType) {
    }

}

export default _VAppRequestApi_stuff_Validators;
