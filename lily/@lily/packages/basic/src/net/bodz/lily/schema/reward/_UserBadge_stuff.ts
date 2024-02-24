import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type User from "../account/User";
import type Badge from "./Badge";
import _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class _UserBadge_stuff extends CoEntity<integer> {
    static TYPE = new _UserBadge_stuff_TypeInfo();

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
