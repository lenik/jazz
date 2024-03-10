import { BIG_DECIMAL } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import SalesOrder from "../shop/SalesOrder";
import SalesOrderItem from "../shop/SalesOrderItem";
import TransportOrderItemValidators from "./TransportOrderItemValidators";
import _TransportOrderItem_stuff_TypeInfo from "./_TransportOrderItem_stuff_TypeInfo";

export class TransportOrderItemTypeInfo extends _TransportOrderItem_stuff_TypeInfo {

    readonly validators = new TransportOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, validator: this.validators.validateAmount }),
            salesOrder: property({ type: SalesOrder.TYPE, validator: this.validators.validateSalesOrder }),
            salesOrderItem: property({ type: SalesOrderItem.TYPE, validator: this.validators.validateSalesOrderItem }),
        });
    }

    static readonly INSTANCE = new TransportOrderItemTypeInfo();

}

export default TransportOrderItemTypeInfo;
