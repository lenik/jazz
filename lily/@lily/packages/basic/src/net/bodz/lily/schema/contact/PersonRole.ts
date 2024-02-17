
import { * as validators } from "./PersonValidators";
import type { _PersonRole_stuff } from "./_PersonRole_stuff";

export class PersonRole extends _PersonRole_stuff {
    static TYPE = new PersonRoleType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
