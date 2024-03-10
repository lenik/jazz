import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type Dim3d from "../art/Dim3d";
import type RegionTypeInfo from "./RegionTypeInfo";
import _Region_stuff_Validators from "./_Region_stuff_Validators";

export class RegionValidators extends _Region_stuff_Validators {

    constructor(type: RegionTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as RegionTypeInfo;
    }

    validateBbox(val: Dim3d) {
    }

    validateFullPath(val: string) {
    }

    validatePosition(val: Dim3d) {
    }

}

export default RegionValidators;
