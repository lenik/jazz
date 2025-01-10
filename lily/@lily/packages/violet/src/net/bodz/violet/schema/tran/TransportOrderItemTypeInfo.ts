import { BIG_DECIMAL } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { SalesOrderItem_TYPE } from "../shop/SalesOrderItemTypeInfo";
import { SalesOrder_TYPE } from "../shop/SalesOrderTypeInfo";
import TransportOrderItem from "./TransportOrderItem";
import TransportOrderItemValidators from "./TransportOrderItemValidators";
import _TransportOrderItem_stuff_TypeInfo from "./_TransportOrderItem_stuff_TypeInfo";

export class TransportOrderItemTypeInfo extends _TransportOrderItem_stuff_TypeInfo {

    readonly validators = new TransportOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrderItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TransportOrderItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, precision: 20, scale: 2, validator: this.validators.validateAmount }),
            salesOrder: property({ type: SalesOrder_TYPE, validator: this.validators.validateSalesOrder }),
            salesOrderItem: property({ type: SalesOrderItem_TYPE, validator: this.validators.validateSalesOrderItem }),
        });
    }

    static readonly INSTANCE = new TransportOrderItemTypeInfo();

}

export default TransportOrderItemTypeInfo;

export const TransportOrderItem_TYPE = TransportOrderItemTypeInfo.INSTANCE;
