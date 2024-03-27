import Badge from "./Badge";
import BadgeValidators from "./BadgeValidators";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class BadgeTypeInfo extends _Badge_stuff_TypeInfo {

    readonly validators = new BadgeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.reward.Badge"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Badge();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new BadgeTypeInfo();

}

export default BadgeTypeInfo;
