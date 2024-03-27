import IValidControl from "@lily/basic/src/net/bodz/lily/concrete/util/IValidControl";
import ArtifactModelTypeInfo from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";
import FabCost from "@lily/violet/src/net/bodz/violet/schema/art/FabCost";
import _ArtifactModel_stuff from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff";

export class ArtifactModel extends _ArtifactModel_stuff {

    static _typeInfo: ArtifactModelTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactModelTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    cost?: FabCost
    modelName?: string
    validControl?: IValidControl

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactModel;
