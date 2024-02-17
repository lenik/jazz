
import { * as validators } from "./PersonValidators";
import type { _UserType_stuff } from "./_UserType_stuff";

export class UserType extends _UserType_stuff {
    static TYPE = new UserTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
