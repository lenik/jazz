import FormDef from "./FormDef";
import FormDefValidators from "./FormDefValidators";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class FormDefTypeInfo extends _FormDef_stuff_TypeInfo {

    readonly validators = new FormDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.FormDef"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new FormDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new FormDefTypeInfo();

}

export default FormDefTypeInfo;

export const FormDef_TYPE = FormDefTypeInfo.INSTANCE;
