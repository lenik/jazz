import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class _UserRun_stuff extends CoEntity<integer> {
    static TYPE = new _UserRun_stuff_TypeInfo();

    score: integer;
    lastLoginTime?: Date;
    lastLoginIP?: string;

    user: User;
    userId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserRun_stuff;
