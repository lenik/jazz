import DiaryReviewVoteTypeInfo from "./DiaryReviewVoteTypeInfo";
import _DiaryReviewVote_stuff from "./_DiaryReviewVote_stuff";

export class DiaryReviewVote extends _DiaryReviewVote_stuff {

    static _typeInfo: DiaryReviewVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryReviewVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default DiaryReviewVote;
