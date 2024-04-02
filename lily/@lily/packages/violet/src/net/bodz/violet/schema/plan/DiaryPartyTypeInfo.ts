import DiaryParty from "./DiaryParty";
import DiaryPartyValidators from "./DiaryPartyValidators";
import _DiaryParty_stuff_TypeInfo from "./_DiaryParty_stuff_TypeInfo";

export class DiaryPartyTypeInfo extends _DiaryParty_stuff_TypeInfo {

    readonly validators = new DiaryPartyValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParty"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryParty();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryPartyTypeInfo();

}

export default DiaryPartyTypeInfo;

export const DiaryParty_TYPE = DiaryPartyTypeInfo.INSTANCE;
