import ParameterDefValidators from "./ParameterDefValidators";
import _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class ParameterDefTypeInfo extends _ParameterDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.ParameterDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Parameter"; }

    validators = new ParameterDefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default ParameterDefTypeInfo;
