import CoTagTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _ArtifactTag_stuff_Validators from "./_ArtifactTag_stuff_Validators";

export class _ArtifactTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "arttag";

    readonly validators = new _ArtifactTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _ArtifactTag_stuff_TypeInfo;
