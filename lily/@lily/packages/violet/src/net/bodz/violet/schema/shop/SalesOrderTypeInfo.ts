import { BIG_DECIMAL, INT, LIST } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import TransportOrder from "../tran/TransportOrder";
import SalesOrderItem from "./SalesOrderItem";
import SalesOrderValidators from "./SalesOrderValidators";
import _SalesOrder_stuff_TypeInfo from "./_SalesOrder_stuff_TypeInfo";

export class SalesOrderTypeInfo extends _SalesOrder_stuff_TypeInfo {

    readonly validators = new SalesOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrder"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            beginTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateBeginTime }),
            deadline: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateDeadline }),
            deliveries: property({ type: LIST(TransportOrder.TYPE), validator: this.validators.validateDeliveries }),
            endTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateEndTime }),
            items: property({ type: LIST(SalesOrderItem.TYPE), validator: this.validators.validateItems }),
            length: property({ type: INT, nullable: false, validator: this.validators.validateLength }),
            orderTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateOrderTime }),
            totalAmount: property({ type: BIG_DECIMAL, validator: this.validators.validateTotalAmount }),
            totalQuantity: property({ type: BIG_DECIMAL, validator: this.validators.validateTotalQuantity }),
        });
    }

    static readonly INSTANCE = new SalesOrderTypeInfo();

}

export default SalesOrderTypeInfo;
