import { DOUBLE, INT, STRING } from 'skel01-core/src/lang/baseinfo';
import { property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import StructRowTypeInfo from '@lily/basic/src/net/bodz/lily/concrete/StructRowTypeInfo';
import Dim3dValidators from './Dim3dValidators';

export class Dim3dTypeInfo extends StructRowTypeInfo {

    readonly validators = new Dim3dValidators(this);

    get name() { return "net.bodz.violet.schema.art.Dim3d"; }
    get icon() { return "fa-cube"; }
    get label() { return "Dim3d"; }
    get description() { return "3D dimension size."; }

    override preamble() {
        this.declare({
            dx: property({ type: DOUBLE, nullable: false, icon: "far-ruler" }),
            dy: property({ type: DOUBLE, nullable: false, icon: "far-ruler" }),
            dz: property({ type: DOUBLE, nullable: false, icon: "far-ruler" }),
        });
    }

    static readonly INSTANCE = new Dim3dTypeInfo();

}

export default Dim3dTypeInfo;
export const Dim3d_TYPE = Dim3dTypeInfo.INSTANCE;
