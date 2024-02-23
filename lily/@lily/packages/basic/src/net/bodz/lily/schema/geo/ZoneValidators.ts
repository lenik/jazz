import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import { GeoLocation } from "@skeljs/dba/src/net/bodz/lily/concrete/util/GeoLocation";

import _Zone_stuffValidators from "./_Zone_stuffValidators";

export class ZoneValidators extends _Zone_stuffValidators {
    validateFullPath(val: string) {
    }
    validateGeo(val: GeoLocation) {
    }
    validateProperties(val: any) {
    }

}

export default ZoneValidators;
