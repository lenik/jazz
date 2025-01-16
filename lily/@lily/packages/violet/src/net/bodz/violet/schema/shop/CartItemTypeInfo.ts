import { BIG_DECIMAL } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import CartItem from "./CartItem";
import CartItemValidators from "./CartItemValidators";
import _CartItem_stuff_TypeInfo from "./_CartItem_stuff_TypeInfo";

export class CartItemTypeInfo extends _CartItem_stuff_TypeInfo {

    readonly validators = new CartItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.CartItem"; }
    get icon() { return "fa-tag"; }
    get description() { return "购物车项"; }

    override create() {
        return new CartItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
            amount: property({ type: BIG_DECIMAL, 
                description: "总额", 
                validator: this.validators.validateAmount }),
            artifact: property({ type: Artifact_TYPE, validator: this.validators.validateArtifact }),
        });
    }

    static readonly INSTANCE = new CartItemTypeInfo();

}

export default CartItemTypeInfo;

export const CartItem_TYPE = CartItemTypeInfo.INSTANCE;
