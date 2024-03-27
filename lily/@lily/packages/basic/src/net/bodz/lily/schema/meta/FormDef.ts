import FormDefTypeInfo from "./FormDefTypeInfo";
import _FormDef_stuff from "./_FormDef_stuff";

export class FormDef extends _FormDef_stuff<FormDef> {

    static _typeInfo: FormDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FormDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FormDef;
