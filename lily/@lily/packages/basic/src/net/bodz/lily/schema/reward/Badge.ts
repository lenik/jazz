
import { * as validators } from "./PersonValidators";
import type { _Badge_stuff } from "./_Badge_stuff";

export class Badge extends _Badge_stuff {
    static TYPE = new BadgeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
