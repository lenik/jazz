import type { int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";

import type FabOrder from "./FabOrder";
import _FabTask_stuff_TypeInfo from "./_FabTask_stuff_TypeInfo";

export class _FabTask_stuff extends CoEvent<long> {

    static _typeInfo: _FabTask_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTask_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    since: Timestamp;
    deadline: Timestamp;
    processCount?: int;
    trackCount?: int;

    order: FabOrder;
    orderId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTask_stuff;
