import { BIG_DECIMAL } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import SalesOrderItemValidators from "./SalesOrderItemValidators";
import _SalesOrderItem_stuff_TypeInfo from "./_SalesOrderItem_stuff_TypeInfo";

export class SalesOrderItemTypeInfo extends _SalesOrderItem_stuff_TypeInfo {

    readonly validators = new SalesOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, validator: this.validators.validateAmount }),
            beginTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateBeginTime }),
            deadline: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateDeadline }),
            endTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateEndTime }),
            orderTime: property({ type: ZonedDateTime.TYPE, validator: this.validators.validateOrderTime }),
        });
    }

    static readonly INSTANCE = new SalesOrderItemTypeInfo();

}

export default SalesOrderItemTypeInfo;
