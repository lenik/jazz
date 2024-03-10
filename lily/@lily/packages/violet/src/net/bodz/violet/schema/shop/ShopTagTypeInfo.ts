import ShopTagValidators from "./ShopTagValidators";
import _ShopTag_stuff_TypeInfo from "./_ShopTag_stuff_TypeInfo";

export class ShopTagTypeInfo extends _ShopTag_stuff_TypeInfo {

    readonly validators = new ShopTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopTagTypeInfo();

}

export default ShopTagTypeInfo;
