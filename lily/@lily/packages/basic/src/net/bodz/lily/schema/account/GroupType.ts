import GroupTypeTypeInfo from "./GroupTypeTypeInfo";
import _GroupType_stuff from "./_GroupType_stuff";

export class GroupType extends _GroupType_stuff {
    static TYPE = new GroupTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default GroupType;
