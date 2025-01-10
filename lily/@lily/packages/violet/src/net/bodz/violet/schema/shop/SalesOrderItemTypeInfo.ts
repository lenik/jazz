import { BIG_DECIMAL } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import SalesOrderItem from "./SalesOrderItem";
import SalesOrderItemValidators from "./SalesOrderItemValidators";
import _SalesOrderItem_stuff_TypeInfo from "./_SalesOrderItem_stuff_TypeInfo";

export class SalesOrderItemTypeInfo extends _SalesOrderItem_stuff_TypeInfo {

    readonly validators = new SalesOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrderItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new SalesOrderItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, precision: 20, scale: 2, validator: this.validators.validateAmount }),
            beginTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateBeginTime }),
            deadline: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateDeadline }),
            endTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateEndTime }),
            orderTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateOrderTime }),
        });
    }

    static readonly INSTANCE = new SalesOrderItemTypeInfo();

}

export default SalesOrderItemTypeInfo;

export const SalesOrderItem_TYPE = SalesOrderItemTypeInfo.INSTANCE;
