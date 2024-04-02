import { INT, LIST } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import TransportOrder from "./TransportOrder";
import { TransportOrderItem_TYPE } from "./TransportOrderItemTypeInfo";
import TransportOrderValidators from "./TransportOrderValidators";
import _TransportOrder_stuff_TypeInfo from "./_TransportOrder_stuff_TypeInfo";

export class TransportOrderTypeInfo extends _TransportOrder_stuff_TypeInfo {

    readonly validators = new TransportOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrder"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TransportOrder();
    }

    override preamble() {
        super.preamble();
        this.declare({
            arrivedDate: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateArrivedDate }),
            items: property({ type: LIST(TransportOrderItem_TYPE), validator: this.validators.validateItems }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            shipDate: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateShipDate }),
        });
    }

    static readonly INSTANCE = new TransportOrderTypeInfo();

}

export default TransportOrderTypeInfo;

export const TransportOrder_TYPE = TransportOrderTypeInfo.INSTANCE;
