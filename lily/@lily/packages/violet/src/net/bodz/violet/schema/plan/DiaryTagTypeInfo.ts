import DiaryTag from "./DiaryTag";
import DiaryTagValidators from "./DiaryTagValidators";
import _DiaryTag_stuff_TypeInfo from "./_DiaryTag_stuff_TypeInfo";

export class DiaryTagTypeInfo extends _DiaryTag_stuff_TypeInfo {

    readonly validators = new DiaryTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryTagTypeInfo();

}

export default DiaryTagTypeInfo;

export const DiaryTag_TYPE = DiaryTagTypeInfo.INSTANCE;
