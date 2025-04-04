import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import CoEvent from "lily-basic/src/net/bodz/lily/concrete/CoEvent";

import type ArtifactModel from "../art/ArtifactModel";
import type FabProcess from "./FabProcess";
import type FabStdProcess from "./FabStdProcess";
import type FabTask from "./FabTask";
import _FabProcess_stuff_TypeInfo from "./_FabProcess_stuff_TypeInfo";

export class _FabProcess_stuff extends CoEvent<long> {

    static _typeInfo: _FabProcess_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabProcess_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    quantity: BigDecimal;
    batch?: JsonVariant;
    since: OffsetDateTime;
    deadline: OffsetDateTime;
    trackCount?: int;

    standard: FabStdProcess;
    standardId: int;

    parent?: FabProcess;
    parentId?: long;

    output: ArtifactModel;
    outputId: int;

    task: FabTask;
    taskId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabProcess_stuff;
