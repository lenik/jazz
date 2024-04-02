import SellPrice from "./SellPrice";
import SellPriceValidators from "./SellPriceValidators";
import _SellPrice_stuff_TypeInfo from "./_SellPrice_stuff_TypeInfo";

export class SellPriceTypeInfo extends _SellPrice_stuff_TypeInfo {

    readonly validators = new SellPriceValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SellPrice"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new SellPrice();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new SellPriceTypeInfo();

}

export default SellPriceTypeInfo;

export const SellPrice_TYPE = SellPriceTypeInfo.INSTANCE;
