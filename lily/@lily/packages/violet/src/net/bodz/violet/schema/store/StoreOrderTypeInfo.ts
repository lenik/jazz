import { BIG_DECIMAL, INT, LIST } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import StoreOrder from "./StoreOrder";
import { StoreOrderItem_TYPE } from "./StoreOrderItemTypeInfo";
import StoreOrderValidators from "./StoreOrderValidators";
import _StoreOrder_stuff_TypeInfo from "./_StoreOrder_stuff_TypeInfo";

export class StoreOrderTypeInfo extends _StoreOrder_stuff_TypeInfo {

    readonly validators = new StoreOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreOrder"; }
    get icon() { return "fa-tag"; }
    get description() { return "库存作业"; }

    override create() {
        return new StoreOrder();
    }

    override preamble() {
        super.preamble();
        this.declare({
            items: property({ type: LIST(StoreOrderItem_TYPE), 
                description: "明细", 
                validator: this.validators.validateItems }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, 
                description: "总金额", 
                validator: this.validators.validateTotalAmount }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, 
                description: "总数量", 
                validator: this.validators.validateTotalQuantity }),
        });
    }

    static readonly INSTANCE = new StoreOrderTypeInfo();

}

export default StoreOrderTypeInfo;

export const StoreOrder_TYPE = StoreOrderTypeInfo.INSTANCE;
