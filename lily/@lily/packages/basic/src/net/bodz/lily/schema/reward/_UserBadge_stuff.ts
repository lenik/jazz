
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { User } from "../account/User";
import type { Badge } from "./Badge";
import type { _UserBadge_stuff_Type } from "./_UserBadge_stuff_Type";

export class _UserBadge_stuff extends CoEntity<Integer> {
    static TYPE = new _UserBadge_stuff_Type();

    id: int;

    badge?: Badge;
    badgeId?: integer;

    user?: User;
    userId?: integer;

    constructor(o: any) {
        super(o);
    }
}
