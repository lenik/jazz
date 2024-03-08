import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class FormParameterTypeInfo extends _FormParameter_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.FormParameter"; }
    get icon() { return "fa-tag"; }

    validators = new FormParameterValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default FormParameterTypeInfo;
