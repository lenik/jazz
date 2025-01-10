import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class _VApiLog_stuff_Validators extends IdEntityValidators {

    constructor(type: _VApiLog_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApiLog_stuff_TypeInfo;
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
