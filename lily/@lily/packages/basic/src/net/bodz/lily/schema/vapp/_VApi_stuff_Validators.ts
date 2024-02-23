import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ApiType } from "./ApiType";
import { VApp } from "./VApp";

export class _VApi_stuff_Validators extends CoEntityValidators {
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
