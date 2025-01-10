import { ValidateResult } from "skel01-core/src/ui/types";

import type IssueFavTypeInfo from "./IssueFavTypeInfo";
import _IssueFav_stuff_Validators from "./_IssueFav_stuff_Validators";

export class IssueFavValidators extends _IssueFav_stuff_Validators {

    constructor(type: IssueFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueFavTypeInfo;
    }

}

export default IssueFavValidators;
