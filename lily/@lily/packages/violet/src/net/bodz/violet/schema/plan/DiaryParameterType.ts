import DiaryParameterTypeTypeInfo from "./DiaryParameterTypeTypeInfo";
import _DiaryParameterType_stuff from "./_DiaryParameterType_stuff";

export class DiaryParameterType extends _DiaryParameterType_stuff<DiaryParameterType> {

    static _typeInfo: DiaryParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryParameterTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default DiaryParameterType;
