import type { List, int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import TransportOrderItem from "./TransportOrderItem";
import TransportOrderTypeInfo from "./TransportOrderTypeInfo";
import _TransportOrder_stuff from "./_TransportOrder_stuff";

export class TransportOrder extends _TransportOrder_stuff {

    static _typeInfo: TransportOrderTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TransportOrderTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    arrivedDate?: ZonedDateTime
    items?: List<TransportOrderItem>
    length: int
    shipDate?: ZonedDateTime

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TransportOrder;
