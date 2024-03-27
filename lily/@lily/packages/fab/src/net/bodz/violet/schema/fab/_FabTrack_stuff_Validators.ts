import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEventValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEventValidators";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";

import type FabProcess from "./FabProcess";
import type _FabTrack_stuff_TypeInfo from "./_FabTrack_stuff_TypeInfo";

export class _FabTrack_stuff_Validators extends CoEventValidators {

    constructor(type: _FabTrack_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabTrack_stuff_TypeInfo;
    }

    validateSince(val: OffsetDateTime) {
    }

    validateDeadline(val: OffsetDateTime) {
    }

    validatePlannedQuantity(val: BigDecimal) {
    }

    validateActualQuantity(val: BigDecimal) {
    }

    validateValidQuantity(val: BigDecimal) {
    }

    validateOrgUnit(val: OrgUnit) {
    }

    validateProcess(val: FabProcess) {
    }

}

export default _FabTrack_stuff_Validators;
