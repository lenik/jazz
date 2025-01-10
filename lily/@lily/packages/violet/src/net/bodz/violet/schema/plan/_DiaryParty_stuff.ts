import type { int, long } from "skel01-core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type Diary from "./Diary";
import _DiaryParty_stuff_TypeInfo from "./_DiaryParty_stuff_TypeInfo";

export class _DiaryParty_stuff extends IdEntity<long> {

    static _typeInfo: _DiaryParty_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _DiaryParty_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    value: int;

    person?: Person;
    personId?: int;

    org?: Organization;
    orgId?: int;

    user?: User;
    userId?: int;

    diary: Diary;
    diaryId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _DiaryParty_stuff;
