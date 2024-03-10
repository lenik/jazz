import DiaryVoteTypeInfo from "./DiaryVoteTypeInfo";
import _DiaryVote_stuff from "./_DiaryVote_stuff";

export class DiaryVote extends _DiaryVote_stuff {

    static _typeInfo: DiaryVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default DiaryVote;
