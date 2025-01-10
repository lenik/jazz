import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ZoneCategoryTypeInfo from "./ZoneCategoryTypeInfo";
import _ZoneCategory_stuff_Validators from "./_ZoneCategory_stuff_Validators";

export class ZoneCategoryValidators extends _ZoneCategory_stuff_Validators {

    constructor(type: ZoneCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ZoneCategoryTypeInfo;
    }

}

export default ZoneCategoryValidators;
