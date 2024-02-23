import _ParameterDef_stuff from "./_ParameterDef_stuff";
import { _ParameterDef_stuffTypeInfo } from "./_ParameterDef_stuffTypeInfo";

export class ParameterDef extends _ParameterDef_stuff {
    static TYPE = new _ParameterDef_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterDef;
