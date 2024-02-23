import _GroupType_stuff from "./_GroupType_stuff";
import { _GroupType_stuff_Type } from "./_GroupType_stuff_Type";

export class GroupType extends _GroupType_stuff {
    static TYPE = new _GroupType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default GroupType;
