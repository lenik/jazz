import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

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

    validateProperties(val: any) {
    }

}

export default ZoneValidators;
