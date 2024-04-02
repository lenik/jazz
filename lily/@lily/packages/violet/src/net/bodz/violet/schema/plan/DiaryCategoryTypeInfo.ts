import DiaryCategory from "./DiaryCategory";
import DiaryCategoryValidators from "./DiaryCategoryValidators";
import _DiaryCategory_stuff_TypeInfo from "./_DiaryCategory_stuff_TypeInfo";

export class DiaryCategoryTypeInfo extends _DiaryCategory_stuff_TypeInfo {

    readonly validators = new DiaryCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryCategoryTypeInfo();

}

export default DiaryCategoryTypeInfo;

export const DiaryCategory_TYPE = DiaryCategoryTypeInfo.INSTANCE;
