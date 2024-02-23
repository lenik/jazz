import _Badge_stuff from "./_Badge_stuff";
import { _Badge_stuff_Type } from "./_Badge_stuff_Type";

export class Badge extends _Badge_stuff {
    static TYPE = new _Badge_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Badge;
