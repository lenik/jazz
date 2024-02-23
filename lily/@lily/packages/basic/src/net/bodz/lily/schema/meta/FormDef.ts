import _FormDef_stuff from "./_FormDef_stuff";
import { _FormDef_stuff_Type } from "./_FormDef_stuff_Type";

export class FormDef extends _FormDef_stuff {
    static TYPE = new _FormDef_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormDef;
