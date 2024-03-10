import DiaryParameterValidators from "./DiaryParameterValidators";
import _DiaryParameter_stuff_TypeInfo from "./_DiaryParameter_stuff_TypeInfo";

export class DiaryParameterTypeInfo extends _DiaryParameter_stuff_TypeInfo {

    readonly validators = new DiaryParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryParameterTypeInfo();

}

export default DiaryParameterTypeInfo;
