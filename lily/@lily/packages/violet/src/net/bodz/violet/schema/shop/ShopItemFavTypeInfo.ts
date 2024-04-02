import ShopItemFav from "./ShopItemFav";
import ShopItemFavValidators from "./ShopItemFavValidators";
import _ShopItemFav_stuff_TypeInfo from "./_ShopItemFav_stuff_TypeInfo";

export class ShopItemFavTypeInfo extends _ShopItemFav_stuff_TypeInfo {

    readonly validators = new ShopItemFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItemFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ShopItemFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopItemFavTypeInfo();

}

export default ShopItemFavTypeInfo;

export const ShopItemFav_TYPE = ShopItemFavTypeInfo.INSTANCE;
