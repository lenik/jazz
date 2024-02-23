import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipal from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipal";

import Person from "../contact/Person";
import Group from "./Group";
import User from "./User";
import UserType from "./UserType";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class _User_stuff extends CoPrincipal {
    static TYPE = new _User_stuff_TypeInfo();


    person?: Person;
    personId?: integer;

    primaryGroup: Group;
    primaryGroupId: integer;

    referer?: User;
    refererId?: integer;

    type: UserType;
    typeId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _User_stuff;
