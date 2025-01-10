import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type FabStdTest from "./FabStdTest";
import type _FabStdTestParameter_stuff_TypeInfo from "./_FabStdTestParameter_stuff_TypeInfo";

export class _FabStdTestParameter_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabStdTestParameter_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdTestParameter_stuff_TypeInfo;
    }

    validateRequired(val: boolean) {
    }

    validateProperties(val: JsonVariant) {
    }

    validateExpected(val: string) {
    }

    validateTest(val: FabStdTest) {
    }

}

export default _FabStdTestParameter_stuff_Validators;
