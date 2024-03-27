import FormParameterTypeInfo from "./FormParameterTypeInfo";
import _FormParameter_stuff from "./_FormParameter_stuff";

export class FormParameter extends _FormParameter_stuff {

    static _typeInfo: FormParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FormParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FormParameter;
