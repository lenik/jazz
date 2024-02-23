import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoParameterValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoParameterValidators";

import { TypeParamType } from "../../meta/TypeParamType";

export class _PostParameterType_stuffValidators extends CoParameterValidators {
    validateName(val: string) {
    }

    validateDummy(val: integer) {
    }

}

export default _PostParameterType_stuffValidators;
