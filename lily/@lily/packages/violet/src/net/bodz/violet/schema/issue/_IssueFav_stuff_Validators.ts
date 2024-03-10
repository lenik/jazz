import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type Issue from "./Issue";
import type _IssueFav_stuff_TypeInfo from "./_IssueFav_stuff_TypeInfo";

export class _IssueFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _IssueFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _IssueFav_stuff_TypeInfo;
    }

    validateIssue(val: Issue) {
    }

}

export default _IssueFav_stuff_Validators;
