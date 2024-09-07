import type { int } from "@skeljs/core/src/lang/basetype";
import IArtifactExtrasTypeInfo from "./IArtifactExtrasTypeInfo";

export class IArtifactExtras {

    static readonly TYPE = IArtifactExtrasTypeInfo.INSTANCE;

    caution?: string
    color?: string
    supplyDelay?: int

    constructor(o?: any) {
        if (o != null)
            Object.assign(this, o);
    }

}

export default IArtifactExtras;
