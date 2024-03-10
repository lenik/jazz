import DiaryParameterTypeInfo from "./DiaryParameterTypeInfo";
import _DiaryParameter_stuff from "./_DiaryParameter_stuff";

export class DiaryParameter extends _DiaryParameter_stuff<DiaryParameter> {

    static _typeInfo: DiaryParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default DiaryParameter;
