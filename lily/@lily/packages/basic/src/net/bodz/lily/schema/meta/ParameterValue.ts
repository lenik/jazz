import ParameterValueTypeInfo from "./ParameterValueTypeInfo";
import _ParameterValue_stuff from "./_ParameterValue_stuff";

export class ParameterValue extends _ParameterValue_stuff {
    static TYPE = new ParameterValueTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterValue;
