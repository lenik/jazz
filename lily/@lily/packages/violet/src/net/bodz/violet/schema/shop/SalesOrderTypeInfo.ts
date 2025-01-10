import { BIG_DECIMAL, INT, LIST } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { TransportOrder_TYPE } from "../tran/TransportOrderTypeInfo";
import SalesOrder from "./SalesOrder";
import { SalesOrderItem_TYPE } from "./SalesOrderItemTypeInfo";
import SalesOrderValidators from "./SalesOrderValidators";
import _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class SalesOrderTypeInfo extends _SalesOrder_stuff_TypeInfo {

    readonly validators = new SalesOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrder"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new SalesOrder();
    }

    override preamble() {
        super.preamble();
        this.declare({
            beginTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateBeginTime }),
            deadline: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateDeadline }),
            deliveries: property({ type: LIST(TransportOrder_TYPE), validator: this.validators.validateDeliveries }),
            endTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateEndTime }),
            items: property({ type: LIST(SalesOrderItem_TYPE), validator: this.validators.validateItems }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            orderTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateOrderTime }),
            totalAmount: property({ type: BIG_DECIMAL, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),
            totalQuantity: property({ type: BIG_DECIMAL, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
        });
    }

    static readonly INSTANCE = new SalesOrderTypeInfo();

}

export default SalesOrderTypeInfo;

export const SalesOrder_TYPE = SalesOrderTypeInfo.INSTANCE;
