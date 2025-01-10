import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";

import type SalesOrder from "../shop/SalesOrder";
import type StoreOrder from "../store/StoreOrder";
import AbstractTransportOrder from "./AbstractTransportOrder";
import type TransportCategory from "./TransportCategory";
import type TransportOrder from "./TransportOrder";
import type TransportPhase from "./TransportPhase";
import _TransportOrder_stuff_TypeInfo from "./_TransportOrder_stuff_TypeInfo";

export class _TransportOrder_stuff extends AbstractTransportOrder {

    static _typeInfo: _TransportOrder_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TransportOrder_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    shipcost: BigDecimal;
    length: int;
    totalQuantity: BigDecimal;
    totalAmount: BigDecimal;

    prev?: TransportOrder;
    prevId?: long;

    shipper?: Organization;
    shipperId?: int;

    category: TransportCategory;
    categoryId: int;

    salesOrder?: SalesOrder;
    salesOrderId?: long;

    phase: TransportPhase;
    phaseId: int;

    storeodr?: StoreOrder;
    storeodrId?: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TransportOrder_stuff;
