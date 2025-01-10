import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";

import type ArtifactModel from "../art/ArtifactModel";
import type FabTask from "./FabTask";
import _FabTaskItem_stuff_TypeInfo from "./_FabTaskItem_stuff_TypeInfo";

export class _FabTaskItem_stuff extends CoEvent<long> {

    static _typeInfo: _FabTaskItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTaskItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    deadline: OffsetDateTime;
    status?: string;
    quantity: BigDecimal;
    batch?: JsonVariant;
    trackCount?: int;

    task: FabTask;
    taskId: long;

    model: ArtifactModel;
    modelId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTaskItem_stuff;
