import type { int } from "skel01-core/src/lang/basetype";
import IdEntity from "lily-basic/src/net/bodz/lily/concrete/IdEntity";
import type UomRow from "lily-basic/src/net/bodz/lily/schema/util/UomRow";

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

    uom?: UomRow;
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
