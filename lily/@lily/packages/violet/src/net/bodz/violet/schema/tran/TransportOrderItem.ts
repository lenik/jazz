import type { BigDecimal } from "skel01-core/src/lang/basetype";

import SalesOrder from "../shop/SalesOrder";
import SalesOrderItem from "../shop/SalesOrderItem";
import TransportOrderItemTypeInfo from "./TransportOrderItemTypeInfo";
import _TransportOrderItem_stuff from "./_TransportOrderItem_stuff";

export class TransportOrderItem extends _TransportOrderItem_stuff {

    static _typeInfo: TransportOrderItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TransportOrderItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    amount?: BigDecimal
    salesOrder?: SalesOrder
    salesOrderItem?: SalesOrderItem

    constructor(o?: any) {
        super(o);
    }
}

export default TransportOrderItem;
