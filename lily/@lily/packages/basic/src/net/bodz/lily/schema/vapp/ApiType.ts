
import { * as validators } from "./PersonValidators";
import type { _ApiType_stuff } from "./_ApiType_stuff";

export class ApiType extends _ApiType_stuff {
    static TYPE = new ApiTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
