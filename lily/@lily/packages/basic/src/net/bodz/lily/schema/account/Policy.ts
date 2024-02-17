
import { * as validators } from "./PersonValidators";
import type { _Policy_stuff } from "./_Policy_stuff";

export class Policy extends _Policy_stuff {
    static TYPE = new PolicyType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
