import type { int } from "skel01-core/src/lang/basetype";
import IArtifactExtrasTypeInfo from "./IArtifactExtrasTypeInfo";

export class IArtifactExtras {

    static _typeInfo: IArtifactExtrasTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IArtifactExtrasTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    caution?: string
    color?: string
    supplyDelay?: int

    constructor(o?: any) {
        if (o != null)
            Object.assign(this, o);
    }

}

export default IArtifactExtras;
