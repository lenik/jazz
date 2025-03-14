import ParameterDef from "./ParameterDef";
import ParameterDefValidators from "./ParameterDefValidators";
import _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class ParameterDefTypeInfo extends _ParameterDef_stuff_TypeInfo {

    readonly validators = new ParameterDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.ParameterDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override create() {
        return new ParameterDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ParameterDefTypeInfo();

}

export default ParameterDefTypeInfo;

export const ParameterDef_TYPE = ParameterDefTypeInfo.INSTANCE;
