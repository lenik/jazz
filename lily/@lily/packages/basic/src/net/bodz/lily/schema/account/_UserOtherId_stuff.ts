import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import type UserOtherIdType from "./UserOtherIdType";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class _UserOtherId_stuff extends CoEntity<integer> {
    static TYPE = new _UserOtherId_stuff_TypeInfo();

    id: integer;
    beginTime?: ZonedDateTime;
    endTime?: ZonedDateTime;
    year: integer;
    otherId: string;
    auth?: any;

    type: UserOtherIdType;
    typeId: integer;

    user: User;
    userId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserOtherId_stuff;
