import _FormParameter_stuff from "./_FormParameter_stuff";
import { _FormParameter_stuffTypeInfo } from "./_FormParameter_stuffTypeInfo";

export class FormParameter extends _FormParameter_stuff {
    static TYPE = new _FormParameter_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormParameter;
