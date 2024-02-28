import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
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

    validateProperties(val: JsonVariant) {
    }

}

export default ZoneValidators;
