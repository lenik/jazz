import type { int } from "skel01-core/src/lang/basetype";
import BackrefRecord from "lily-basic/src/net/bodz/lily/concrete/BackrefRecord";
import type ExternalSite from "lily-basic/src/net/bodz/lily/schema/inet/ExternalSite";

import type Artifact from "./Artifact";
import _ArtifactBackref_stuff_TypeInfo from "./_ArtifactBackref_stuff_TypeInfo";

export class _ArtifactBackref_stuff extends BackrefRecord {

    static _typeInfo: _ArtifactBackref_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactBackref_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    key?: string;
    value: int;

    site: ExternalSite;
    siteId: int;

    artifact: Artifact;
    artifactId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactBackref_stuff;
