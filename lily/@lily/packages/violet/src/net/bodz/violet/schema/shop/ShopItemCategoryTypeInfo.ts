import ShopItemCategoryValidators from "./ShopItemCategoryValidators";
import _ShopItemCategory_stuff_TypeInfo from "./_ShopItemCategory_stuff_TypeInfo";

export class ShopItemCategoryTypeInfo extends _ShopItemCategory_stuff_TypeInfo {

    readonly validators = new ShopItemCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopItemCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopItemCategoryTypeInfo();

}

export default ShopItemCategoryTypeInfo;