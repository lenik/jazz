import FabStdTester from "./FabStdTester";
import FabStdTesterValidators from "./FabStdTesterValidators";
import _FabStdTester_stuff_TypeInfo from "./_FabStdTester_stuff_TypeInfo";

export class FabStdTesterTypeInfo extends _FabStdTester_stuff_TypeInfo {

    readonly validators = new FabStdTesterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTester"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabStdTester();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdTesterTypeInfo();

}

export default FabStdTesterTypeInfo;
