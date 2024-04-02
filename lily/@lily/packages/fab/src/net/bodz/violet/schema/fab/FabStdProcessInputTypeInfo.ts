import FabStdProcessInput from "./FabStdProcessInput";
import FabStdProcessInputValidators from "./FabStdProcessInputValidators";
import _FabStdProcessInput_stuff_TypeInfo from "./_FabStdProcessInput_stuff_TypeInfo";

export class FabStdProcessInputTypeInfo extends _FabStdProcessInput_stuff_TypeInfo {

    readonly validators = new FabStdProcessInputValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdProcessInput"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FabStdProcessInput();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FabStdProcessInputTypeInfo();

}

export default FabStdProcessInputTypeInfo;

export const FabStdProcessInput_TYPE = FabStdProcessInputTypeInfo.INSTANCE;
