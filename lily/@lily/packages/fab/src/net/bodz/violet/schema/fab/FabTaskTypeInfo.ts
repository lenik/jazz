import FabTask from "./FabTask";
import FabTaskValidators from "./FabTaskValidators";
import _FabTask_stuff_TypeInfo from "./_FabTask_stuff_TypeInfo";

export class FabTaskTypeInfo extends _FabTask_stuff_TypeInfo {

    readonly validators = new FabTaskValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTask"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabTask();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabTaskTypeInfo();

}

export default FabTaskTypeInfo;

export const FabTask_TYPE = FabTaskTypeInfo.INSTANCE;
