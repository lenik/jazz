import SalesCategoryValidators from "./SalesCategoryValidators";
import _SalesCategory_stuff_TypeInfo from "./_SalesCategory_stuff_TypeInfo";

export class SalesCategoryTypeInfo extends _SalesCategory_stuff_TypeInfo {

    readonly validators = new SalesCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new SalesCategoryTypeInfo();

}

export default SalesCategoryTypeInfo;
