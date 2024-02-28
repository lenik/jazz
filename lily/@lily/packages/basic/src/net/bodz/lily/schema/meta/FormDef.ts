import FormDefTypeInfo from "./FormDefTypeInfo";
import _FormDef_stuff from "./_FormDef_stuff";

export class FormDef extends _FormDef_stuff {
    static _typeInfo: FormDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new FormDefTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormDef;
