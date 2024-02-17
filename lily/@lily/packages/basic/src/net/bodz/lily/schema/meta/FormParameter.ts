
import { * as validators } from "./PersonValidators";
import type { _FormParameter_stuff } from "./_FormParameter_stuff";

export class FormParameter extends _FormParameter_stuff {
    static TYPE = new FormParameterType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
