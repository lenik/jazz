import IssueFavValidators from "./IssueFavValidators";
import _IssueFav_stuff_TypeInfo from "./_IssueFav_stuff_TypeInfo";

export class IssueFavTypeInfo extends _IssueFav_stuff_TypeInfo {

    readonly validators = new IssueFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new IssueFavTypeInfo();

}

export default IssueFavTypeInfo;
