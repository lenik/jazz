import { BIG_DECIMAL } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import StoreOrderItemValidators from "./StoreOrderItemValidators";
import _StoreOrderItem_stuff_TypeInfo from "./_StoreOrderItem_stuff_TypeInfo";

export class StoreOrderItemTypeInfo extends _StoreOrderItem_stuff_TypeInfo {

    readonly validators = new StoreOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreOrderItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, validator: this.validators.validateAmount }),
        });
    }

    static readonly INSTANCE = new StoreOrderItemTypeInfo();

}

export default StoreOrderItemTypeInfo;
