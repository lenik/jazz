import _ParameterValue_stuff from "./_ParameterValue_stuff";
import { _ParameterValue_stuff_Type } from "./_ParameterValue_stuff_Type";

export class ParameterValue extends _ParameterValue_stuff {
    static TYPE = new _ParameterValue_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterValue;
