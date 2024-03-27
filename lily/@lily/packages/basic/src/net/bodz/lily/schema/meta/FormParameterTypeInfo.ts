import FormParameter from "./FormParameter";
import FormParameterValidators from "./FormParameterValidators";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class FormParameterTypeInfo extends _FormParameter_stuff_TypeInfo {

    readonly validators = new FormParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.FormParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FormParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FormParameterTypeInfo();

}

export default FormParameterTypeInfo;
