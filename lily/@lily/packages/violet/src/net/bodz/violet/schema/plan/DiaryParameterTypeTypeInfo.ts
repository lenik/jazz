import DiaryParameterType from "./DiaryParameterType";
import DiaryParameterTypeValidators from "./DiaryParameterTypeValidators";
import _DiaryParameterType_stuff_TypeInfo from "./_DiaryParameterType_stuff_TypeInfo";

export class DiaryParameterTypeTypeInfo extends _DiaryParameterType_stuff_TypeInfo {

    readonly validators = new DiaryParameterTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParameterType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryParameterType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryParameterTypeTypeInfo();

}

export default DiaryParameterTypeTypeInfo;

export const DiaryParameterType_TYPE = DiaryParameterTypeTypeInfo.INSTANCE;
