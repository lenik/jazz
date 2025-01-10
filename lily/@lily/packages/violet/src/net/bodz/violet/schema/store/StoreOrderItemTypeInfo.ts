import { BIG_DECIMAL } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import StoreOrderItem from "./StoreOrderItem";
import StoreOrderItemValidators from "./StoreOrderItemValidators";
import _StoreOrderItem_stuff_TypeInfo from "./_StoreOrderItem_stuff_TypeInfo";

export class StoreOrderItemTypeInfo extends _StoreOrderItem_stuff_TypeInfo {

    readonly validators = new StoreOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreOrderItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new StoreOrderItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, precision: 20, scale: 2, validator: this.validators.validateAmount }),
        });
    }

    static readonly INSTANCE = new StoreOrderItemTypeInfo();

}

export default StoreOrderItemTypeInfo;

export const StoreOrderItem_TYPE = StoreOrderItemTypeInfo.INSTANCE;
