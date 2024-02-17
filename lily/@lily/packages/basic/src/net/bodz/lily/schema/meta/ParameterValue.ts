
import { * as validators } from "./PersonValidators";
import type { _ParameterValue_stuff } from "./_ParameterValue_stuff";

export class ParameterValue extends _ParameterValue_stuff {
    static TYPE = new ParameterValueType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
