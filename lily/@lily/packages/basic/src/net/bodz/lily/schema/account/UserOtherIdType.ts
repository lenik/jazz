
import { * as validators } from "./PersonValidators";
import type { _UserOtherIdType_stuff } from "./_UserOtherIdType_stuff";

export class UserOtherIdType extends _UserOtherIdType_stuff {
    static TYPE = new UserOtherIdTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
