import ParameterValue from "./ParameterValue";
import ParameterValueValidators from "./ParameterValueValidators";
import _ParameterValue_stuff_TypeInfo from "./_ParameterValue_stuff_TypeInfo";

export class ParameterValueTypeInfo extends _ParameterValue_stuff_TypeInfo {

    readonly validators = new ParameterValueValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.ParameterValue"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new ParameterValue();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new ParameterValueTypeInfo();

}

export default ParameterValueTypeInfo;

export const ParameterValue_TYPE = ParameterValueTypeInfo.INSTANCE;
