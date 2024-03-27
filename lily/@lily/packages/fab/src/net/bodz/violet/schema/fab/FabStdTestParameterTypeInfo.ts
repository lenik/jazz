import FabStdTestParameter from "./FabStdTestParameter";
import FabStdTestParameterValidators from "./FabStdTestParameterValidators";
import _FabStdTestParameter_stuff_TypeInfo from "./_FabStdTestParameter_stuff_TypeInfo";

export class FabStdTestParameterTypeInfo extends _FabStdTestParameter_stuff_TypeInfo {

    readonly validators = new FabStdTestParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTestParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabStdTestParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdTestParameterTypeInfo();

}

export default FabStdTestParameterTypeInfo;
