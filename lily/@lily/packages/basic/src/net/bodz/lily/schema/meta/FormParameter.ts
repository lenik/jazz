import FormParameterTypeInfo from "./FormParameterTypeInfo";
import _FormParameter_stuff from "./_FormParameter_stuff";

export class FormParameter extends _FormParameter_stuff {
    static TYPE = new FormParameterTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormParameter;
