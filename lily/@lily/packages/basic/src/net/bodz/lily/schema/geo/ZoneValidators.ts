import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import { GeoLocation } from "@skeljs/dba/src/net/bodz/lily/concrete/util/GeoLocation";

import _Zone_stuff_Validators from "./_Zone_stuff_Validators";

export class ZoneValidators extends _Zone_stuff_Validators {
    validateFullPath(val: string) {
    }
    validateGeo(val: GeoLocation) {
    }
    validateProperties(val: any) {
    }

}

export default ZoneValidators;
