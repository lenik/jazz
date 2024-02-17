
import { * as validators } from "./PersonValidators";
import type { _UserRun_stuff } from "./_UserRun_stuff";

export class UserRun extends _UserRun_stuff {
    static TYPE = new UserRunType();

    activeTime?: Moment
    stateText?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
