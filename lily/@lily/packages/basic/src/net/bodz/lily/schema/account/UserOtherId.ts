
import { * as validators } from "./PersonValidators";
import type { _UserOtherId_stuff } from "./_UserOtherId_stuff";

export class UserOtherId extends _UserOtherId_stuff {
    static TYPE = new UserOtherIdType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
