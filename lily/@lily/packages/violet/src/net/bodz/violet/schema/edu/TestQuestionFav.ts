import TestQuestionFavTypeInfo from "./TestQuestionFavTypeInfo";
import _TestQuestionFav_stuff from "./_TestQuestionFav_stuff";

export class TestQuestionFav extends _TestQuestionFav_stuff {

    static _typeInfo: TestQuestionFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TestQuestionFav;
