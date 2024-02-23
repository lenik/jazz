import _GroupType_stuff from "./_GroupType_stuff";
import { _GroupType_stuffTypeInfo } from "./_GroupType_stuffTypeInfo";

export class GroupType extends _GroupType_stuff {
    static TYPE = new _GroupType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default GroupType;
