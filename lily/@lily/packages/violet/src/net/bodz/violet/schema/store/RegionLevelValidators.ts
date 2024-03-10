import { ValidateResult } from "@skeljs/core/src/ui/types";

import type RegionLevelTypeInfo from "./RegionLevelTypeInfo";
import _RegionLevel_stuff_Validators from "./_RegionLevel_stuff_Validators";

export class RegionLevelValidators extends _RegionLevel_stuff_Validators {

    constructor(type: RegionLevelTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as RegionLevelTypeInfo;
    }

}

export default RegionLevelValidators;
