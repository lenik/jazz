import type { int } from "skel01-core/src/lang/basetype";
import DocRecord from "lily-basic/src/net/bodz/lily/concrete/DocRecord";

import type Artifact from "./Artifact";
import _ArtifactDoc_stuff_TypeInfo from "./_ArtifactDoc_stuff_TypeInfo";

export class _ArtifactDoc_stuff extends DocRecord {

    static _typeInfo: _ArtifactDoc_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactDoc_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactDoc_stuff;
