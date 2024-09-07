import type { double, int } from "@skeljs/core/src/lang/basetype";
import Dim3dTypeInfo from "./Dim3dTypeInfo";

export class Dim3d {

    static readonly TYPE = Dim3dTypeInfo.INSTANCE;

    dx: double
    dy: double
    dz: double

    constructor(o?: any) {
        if (o != null)
            Object.assign(this, o);
    }

}

export default Dim3d;
