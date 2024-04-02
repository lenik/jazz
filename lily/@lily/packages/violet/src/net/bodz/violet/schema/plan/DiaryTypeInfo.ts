import { LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import Diary from "./Diary";
import { DiaryParty_TYPE } from "./DiaryPartyTypeInfo";
import DiaryValidators from "./DiaryValidators";
import _Diary_stuff_TypeInfo from "./_Diary_stuff_TypeInfo";

export class DiaryTypeInfo extends _Diary_stuff_TypeInfo {

    readonly validators = new DiaryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.Diary"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Diary();
    }

    override preamble() {
        super.preamble();
        this.declare({
            parties: property({ type: LIST(DiaryParty_TYPE), validator: this.validators.validateParties }),
        });
    }

    static readonly INSTANCE = new DiaryTypeInfo();

}

export default DiaryTypeInfo;

export const Diary_TYPE = DiaryTypeInfo.INSTANCE;
