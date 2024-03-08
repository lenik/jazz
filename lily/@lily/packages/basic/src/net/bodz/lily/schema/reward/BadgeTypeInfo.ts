import BadgeValidators from "./BadgeValidators";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class BadgeTypeInfo extends _Badge_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.reward.Badge"; }
    get icon() { return "fa-tag"; }

    validators = new BadgeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default BadgeTypeInfo;
