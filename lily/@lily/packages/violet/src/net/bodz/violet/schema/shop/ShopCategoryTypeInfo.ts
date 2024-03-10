import ShopCategoryValidators from "./ShopCategoryValidators";
import _ShopCategory_stuff_TypeInfo from "./_ShopCategory_stuff_TypeInfo";

export class ShopCategoryTypeInfo extends _ShopCategory_stuff_TypeInfo {

    readonly validators = new ShopCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.ShopCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopCategoryTypeInfo();

}

export default ShopCategoryTypeInfo;
