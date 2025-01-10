import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";

import type FabProcess from "./FabProcess";
import _FabTrack_stuff_TypeInfo from "./_FabTrack_stuff_TypeInfo";

export class _FabTrack_stuff extends CoEvent<long> {

    static _typeInfo: _FabTrack_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTrack_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    since: OffsetDateTime;
    deadline: OffsetDateTime;
    plannedQuantity: BigDecimal;
    actualQuantity: BigDecimal;
    validQuantity: BigDecimal;

    orgUnit?: OrgUnit;
    orgUnitId?: int;

    process: FabProcess;
    processId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTrack_stuff;
