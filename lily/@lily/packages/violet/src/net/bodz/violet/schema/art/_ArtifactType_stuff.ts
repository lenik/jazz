import type { int } from "@skeljs/core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import type Uom from "@lily/basic/src/net/bodz/lily/schema/util/Uom";

import type ArtifactCategory from "./ArtifactCategory";
import type ArtifactType from "./ArtifactType";
import _ArtifactType_stuff_TypeInfo from "./_ArtifactType_stuff_TypeInfo";

export class _ArtifactType_stuff extends IdEntity<int> {

    static _typeInfo: _ArtifactType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArtifactType_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    uom?: Uom;
    uomId?: int;

    parent?: ArtifactType;
    parentId?: int;

    category?: ArtifactCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactType_stuff;