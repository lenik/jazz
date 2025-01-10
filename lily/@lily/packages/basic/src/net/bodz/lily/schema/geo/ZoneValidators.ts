import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type GeoLocation from "../../concrete/util/GeoLocation";
import type ZoneTypeInfo from "./ZoneTypeInfo";
import _Zone_stuff_Validators from "./_Zone_stuff_Validators";

export class ZoneValidators extends _Zone_stuff_Validators {

    constructor(type: ZoneTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ZoneTypeInfo;
    }

    validateFullPath(val: string) {
    }

    validateGeo(val: GeoLocation) {
    }

}

export default ZoneValidators;
