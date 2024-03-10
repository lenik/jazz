import FabStdProcessValidators from "./FabStdProcessValidators";
import _FabStdProcess_stuff_TypeInfo from "./_FabStdProcess_stuff_TypeInfo";

export class FabStdProcessTypeInfo extends _FabStdProcess_stuff_TypeInfo {

    readonly validators = new FabStdProcessValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdProcess"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdProcessTypeInfo();

}

export default FabStdProcessTypeInfo;
