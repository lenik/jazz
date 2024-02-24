import FormDefTypeInfo from "./FormDefTypeInfo";
import _FormDef_stuff from "./_FormDef_stuff";

export class FormDef extends _FormDef_stuff {
    static TYPE = new FormDefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormDef;
