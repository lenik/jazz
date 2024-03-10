import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { InetAddress, int } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type User from "./User";
import type _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class _UserRun_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserRun_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserRun_stuff_TypeInfo;
    }

    validateProperties(val: JsonVariant) {
    }

    validateScore(val: int) {
    }

    validateLastLoginTime(val: Timestamp) {
    }

    validateLastLoginIP(val: InetAddress) {
    }

    validateUser(val: User) {
    }

}

export default _UserRun_stuff_Validators;
