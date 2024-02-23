import _ParameterDef_stuff from "./_ParameterDef_stuff";
import { _ParameterDef_stuff_Type } from "./_ParameterDef_stuff_Type";

export class ParameterDef extends _ParameterDef_stuff {
    static TYPE = new _ParameterDef_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterDef;
