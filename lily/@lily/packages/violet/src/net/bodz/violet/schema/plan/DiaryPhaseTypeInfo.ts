import DiaryPhaseValidators from "./DiaryPhaseValidators";
import _DiaryPhase_stuff_TypeInfo from "./_DiaryPhase_stuff_TypeInfo";

export class DiaryPhaseTypeInfo extends _DiaryPhase_stuff_TypeInfo {

    readonly validators = new DiaryPhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryPhaseTypeInfo();

}

export default DiaryPhaseTypeInfo;
