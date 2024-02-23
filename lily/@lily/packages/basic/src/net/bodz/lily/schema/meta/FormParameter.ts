import _FormParameter_stuff from "./_FormParameter_stuff";
import { _FormParameter_stuff_Type } from "./_FormParameter_stuff_Type";

export class FormParameter extends _FormParameter_stuff {
    static TYPE = new _FormParameter_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FormParameter;
