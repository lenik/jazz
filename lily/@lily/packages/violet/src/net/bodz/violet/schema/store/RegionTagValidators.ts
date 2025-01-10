import { ValidateResult } from "skel01-core/src/ui/types";

import type RegionTagTypeInfo from "./RegionTagTypeInfo";
import _RegionTag_stuff_Validators from "./_RegionTag_stuff_Validators";

export class RegionTagValidators extends _RegionTag_stuff_Validators {

    constructor(type: RegionTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as RegionTagTypeInfo;
    }

}

export default RegionTagValidators;
