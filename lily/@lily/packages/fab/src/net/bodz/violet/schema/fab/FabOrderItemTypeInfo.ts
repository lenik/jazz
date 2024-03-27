import FabOrderItem from "./FabOrderItem";
import FabOrderItemValidators from "./FabOrderItemValidators";
import _FabOrderItem_stuff_TypeInfo from "./_FabOrderItem_stuff_TypeInfo";

export class FabOrderItemTypeInfo extends _FabOrderItem_stuff_TypeInfo {

    readonly validators = new FabOrderItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabOrderItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabOrderItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabOrderItemTypeInfo();

}

export default FabOrderItemTypeInfo;
