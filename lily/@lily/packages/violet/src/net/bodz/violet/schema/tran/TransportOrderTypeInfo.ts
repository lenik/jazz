import { INT, LIST } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import TransportOrderItem from "./TransportOrderItem";
import TransportOrderValidators from "./TransportOrderValidators";
import _TransportOrder_stuff_TypeInfo from "./_TransportOrder_stuff_TypeInfo";

export class TransportOrderTypeInfo extends _TransportOrder_stuff_TypeInfo {

    readonly validators = new TransportOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportOrder"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            arrivedDate: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateArrivedDate }),
            items: property({ type: LIST(TransportOrderItem.TYPE), validator: this.validators.validateItems }),
            length: property({ type: INT, nullable: false, validator: this.validators.validateLength }),
            shipDate: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateShipDate }),
        });
    }

    static readonly INSTANCE = new TransportOrderTypeInfo();

}

export default TransportOrderTypeInfo;
