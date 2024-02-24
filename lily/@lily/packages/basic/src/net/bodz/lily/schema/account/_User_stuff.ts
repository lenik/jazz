import type { integer } from "@skeljs/core/src/lang/type";

import CoPrincipal from "../../concrete/CoPrincipal";
import type Person from "../contact/Person";
import type Group from "./Group";
import type User from "./User";
import type UserType from "./UserType";
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
