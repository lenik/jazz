import _Badge_stuff from "./_Badge_stuff";
import { _Badge_stuffTypeInfo } from "./_Badge_stuffTypeInfo";

export class Badge extends _Badge_stuff {
    static TYPE = new _Badge_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Badge;
