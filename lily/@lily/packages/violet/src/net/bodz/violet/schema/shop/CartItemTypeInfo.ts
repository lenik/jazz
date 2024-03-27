import { BIG_DECIMAL } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import Artifact from "../art/Artifact";
import CartItemValidators from "./CartItemValidators";
import _CartItem_stuff_TypeInfo from "./_CartItem_stuff_TypeInfo";

export class CartItemTypeInfo extends _CartItem_stuff_TypeInfo {

    readonly validators = new CartItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.CartItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, validator: this.validators.validateAmount }),
            artifact: property({ type: Artifact.TYPE, validator: this.validators.validateArtifact }),
        });
    }

    static readonly INSTANCE = new CartItemTypeInfo();

}

export default CartItemTypeInfo;