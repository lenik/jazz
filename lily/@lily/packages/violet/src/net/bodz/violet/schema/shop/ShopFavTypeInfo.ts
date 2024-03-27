import ShopFavValidators from "./ShopFavValidators";
import _ShopFav_stuff_TypeInfo from "./_ShopFav_stuff_TypeInfo";

export class ShopFavTypeInfo extends _ShopFav_stuff_TypeInfo {

    readonly validators = new ShopFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopFavTypeInfo();

}

export default ShopFavTypeInfo;