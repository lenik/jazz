import type { long } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityValidators";

import { ApiType } from "./ApiType";
import { VApp } from "./VApp";

export class _VApiLog_stuffValidators extends CoEntityValidators {
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

export default _VApiLog_stuffValidators;
