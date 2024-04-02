import Shop from "./Shop";
import ShopValidators from "./ShopValidators";
import _Shop_stuff_TypeInfo from "./_Shop_stuff_TypeInfo";

export class ShopTypeInfo extends _Shop_stuff_TypeInfo {

    readonly validators = new ShopValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.Shop"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Shop();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ShopTypeInfo();

}

export default ShopTypeInfo;

export const Shop_TYPE = ShopTypeInfo.INSTANCE;
