import type { List, int } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";

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

    arrivedDate?: OffsetDateTime
    items?: List<TransportOrderItem>
    length: int
    shipDate?: OffsetDateTime

    constructor(o?: any) {
        super(o);
    }
}

export default TransportOrder;
