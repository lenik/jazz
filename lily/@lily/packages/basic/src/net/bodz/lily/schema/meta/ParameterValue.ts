import _ParameterValue_stuff from "./_ParameterValue_stuff";
import { _ParameterValue_stuffTypeInfo } from "./_ParameterValue_stuffTypeInfo";

export class ParameterValue extends _ParameterValue_stuff {
    static TYPE = new _ParameterValue_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterValue;
