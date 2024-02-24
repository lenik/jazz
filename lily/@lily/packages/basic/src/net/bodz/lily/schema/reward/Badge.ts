import BadgeTypeInfo from "./BadgeTypeInfo";
import _Badge_stuff from "./_Badge_stuff";

export class Badge extends _Badge_stuff {
    static TYPE = new BadgeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Badge;
