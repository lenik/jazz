import PlanFav from "./PlanFav";
import PlanFavValidators from "./PlanFavValidators";
import _PlanFav_stuff_TypeInfo from "./_PlanFav_stuff_TypeInfo";

export class PlanFavTypeInfo extends _PlanFav_stuff_TypeInfo {

    readonly validators = new PlanFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanFavTypeInfo();

}

export default PlanFavTypeInfo;
