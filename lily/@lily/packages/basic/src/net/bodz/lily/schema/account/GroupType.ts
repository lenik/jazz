
import { * as validators } from "./PersonValidators";
import type { _GroupType_stuff } from "./_GroupType_stuff";

export class GroupType extends _GroupType_stuff {
    static TYPE = new GroupTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
