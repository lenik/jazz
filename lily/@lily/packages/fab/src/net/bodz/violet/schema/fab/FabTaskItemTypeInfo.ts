import FabTaskItem from "./FabTaskItem";
import FabTaskItemValidators from "./FabTaskItemValidators";
import _FabTaskItem_stuff_TypeInfo from "./_FabTaskItem_stuff_TypeInfo";

export class FabTaskItemTypeInfo extends _FabTaskItem_stuff_TypeInfo {

    readonly validators = new FabTaskItemValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTaskItem"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabTaskItem();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTaskItemTypeInfo();

}

export default FabTaskItemTypeInfo;
