import ArtifactCategoryTypeInfo from "./ArtifactCategoryTypeInfo";
import _ArtifactCategory_stuff from "./_ArtifactCategory_stuff";

export class ArtifactCategory extends _ArtifactCategory_stuff<ArtifactCategory> {

    static _typeInfo: ArtifactCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArtifactCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ArtifactCategory;
