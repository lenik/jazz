import FormDefValidators from "./FormDefValidators";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class FormDefTypeInfo extends _FormDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.FormDef"; }
    get icon() { return "fa-tag"; }

    validators = new FormDefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default FormDefTypeInfo;
