import GroupTypeInfo from "./GroupTypeInfo";
import User from "./User";
import _Group_stuff from "./_Group_stuff";

export class Group extends _Group_stuff {
    static TYPE = new GroupTypeInfo();

    children?: Group[]
    users?: User[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Group;
