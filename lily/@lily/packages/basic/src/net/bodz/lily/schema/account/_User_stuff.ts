
import type { CoPrincipal } from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipal";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Person } from "../contact/Person";
import type { Group } from "./Group";
import type { User } from "./User";
import type { UserType } from "./UserType";
import type { _User_stuff_Type } from "./_User_stuff_Type";

export class _User_stuff extends CoPrincipal {
    static TYPE = new _User_stuff_Type();


    person?: Person;
    personId?: integer;

    primaryGroup: Group;
    primaryGroupId: int;

    referer?: User;
    refererId?: integer;

    type: UserType;
    typeId: int;

    constructor(o: any) {
        super(o);
    }
}
