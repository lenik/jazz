import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import User from "../account/User";
import Badge from "./Badge";
import _UserBadge_stuff_Type from "./_UserBadge_stuff_Type";

export class _UserBadge_stuff extends CoEntity<integer> {
    static TYPE = new _UserBadge_stuff_Type();

    id: integer;

    badge?: Badge;
    badgeId?: integer;

    user?: User;
    userId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserBadge_stuff;
