import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoImagedValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type ArtifactModel from "../art/ArtifactModel";
import type FabFn from "./FabFn";
import type FabStdTest from "./FabStdTest";
import type _FabStdProcess_stuff_TypeInfo from "./_FabStdProcess_stuff_TypeInfo";

export class _FabStdProcess_stuff_Validators extends CoImagedValidators {

    constructor(type: _FabStdProcess_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdProcess_stuff_TypeInfo;
    }

    validateValid(val: boolean) {
    }

    validateValidSince(val: ZonedDateTime) {
    }

    validateValidUntil(val: ZonedDateTime) {
    }

    validateDuration(val: int) {
    }

    validateStrict(val: boolean) {
    }

    validateOutput(val: ArtifactModel) {
    }

    validateTest(val: FabStdTest) {
    }

    validateFunction(val: FabFn) {
    }

}

export default _FabStdProcess_stuff_Validators;