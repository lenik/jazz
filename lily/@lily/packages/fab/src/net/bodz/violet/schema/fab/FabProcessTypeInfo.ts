import FabProcess from "./FabProcess";
import FabProcessValidators from "./FabProcessValidators";
import _FabProcess_stuff_TypeInfo from "./_FabProcess_stuff_TypeInfo";

export class FabProcessTypeInfo extends _FabProcess_stuff_TypeInfo {

    readonly validators = new FabProcessValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabProcess"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabProcess();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabProcessTypeInfo();

}

export default FabProcessTypeInfo;

export const FabProcess_TYPE = FabProcessTypeInfo.INSTANCE;
