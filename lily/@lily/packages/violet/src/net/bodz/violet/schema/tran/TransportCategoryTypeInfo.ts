import TransportCategoryValidators from "./TransportCategoryValidators";
import _TransportCategory_stuff_TypeInfo from "./_TransportCategory_stuff_TypeInfo";

export class TransportCategoryTypeInfo extends _TransportCategory_stuff_TypeInfo {

    readonly validators = new TransportCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TransportCategoryTypeInfo();

}

export default TransportCategoryTypeInfo;
