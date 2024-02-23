import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ApiType } from "./ApiType";
import { VAppRequest } from "./VAppRequest";

export class _VAppRequestApi_stuff_Validators extends CoEntityValidators {
    validateId(val: long) {
    }

    validateParent(val: VAppRequest) {
    }

    validateApi(val: ApiType) {
    }

}

export default _VAppRequestApi_stuff_Validators;
