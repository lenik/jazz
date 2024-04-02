import FabOrder from "./FabOrder";
import FabOrderValidators from "./FabOrderValidators";
import _FabOrder_stuff_TypeInfo from "./_FabOrder_stuff_TypeInfo";

export class FabOrderTypeInfo extends _FabOrder_stuff_TypeInfo {

    readonly validators = new FabOrderValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabOrder"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabOrder();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabOrderTypeInfo();

}

export default FabOrderTypeInfo;

export const FabOrder_TYPE = FabOrderTypeInfo.INSTANCE;
