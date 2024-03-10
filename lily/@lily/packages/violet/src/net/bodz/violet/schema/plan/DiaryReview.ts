import DiaryReviewTypeInfo from "./DiaryReviewTypeInfo";
import _DiaryReview_stuff from "./_DiaryReview_stuff";

export class DiaryReview extends _DiaryReview_stuff {

    static _typeInfo: DiaryReviewTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryReviewTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default DiaryReview;
