
import { * as validators } from "./PersonValidators";
import type { _Organization_stuff } from "./_Organization_stuff";

export class Organization extends _Organization_stuff {
    static TYPE = new OrganizationType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
