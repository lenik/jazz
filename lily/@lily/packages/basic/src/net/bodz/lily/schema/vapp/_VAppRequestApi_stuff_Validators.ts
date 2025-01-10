import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type ApiType from "./ApiType";
import type VAppRequest from "./VAppRequest";
import type _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class _VAppRequestApi_stuff_Validators extends IdEntityValidators {

    constructor(type: _VAppRequestApi_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VAppRequestApi_stuff_TypeInfo;
    }

    validateParent(val: VAppRequest) {
    }

    validateApi(val: ApiType) {
    }

}

export default _VAppRequestApi_stuff_Validators;
