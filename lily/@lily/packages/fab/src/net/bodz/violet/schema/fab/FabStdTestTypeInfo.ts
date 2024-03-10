import FabStdTestValidators from "./FabStdTestValidators";
import _FabStdTest_stuff_TypeInfo from "./_FabStdTest_stuff_TypeInfo";

export class FabStdTestTypeInfo extends _FabStdTest_stuff_TypeInfo {

    readonly validators = new FabStdTestValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdTestTypeInfo();

}

export default FabStdTestTypeInfo;
