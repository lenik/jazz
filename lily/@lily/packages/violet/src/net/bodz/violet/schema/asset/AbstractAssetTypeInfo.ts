import { BIG_DECIMAL, INT, LIST, LONG } from 'skel01-core/src/lang/baseinfo';
import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoEventTypeInfo from '@lily/basic/src/net/bodz/lily/concrete/CoEventTypeInfo';
import AbstractAssetValidators from './AbstractAssetValidators';
import Artifact from '../art/Artifact';
import Region from '../store/Region';
import { ZonedDateTime } from 'skel01-core/src/lang/time';

export class AbstractAssetTypeInfo extends CoEventTypeInfo {

    readonly validators = new AbstractAssetValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.asset.AbstractAsset"; }
    get icon() { return "fa-image"; }
    get label() { return "Abstract Asset"; }
    get description() { return "Someone owned asset"; }

    override preamble() {
        super.preamble();
        this.declare({
            artifact: property({ type: Artifact.TYPE, nullable: false, icon: "far-tag" }),
            region: property({ type: Region.TYPE, nullable: true, icon: "far-tag" }),
            quantity: property({ type: BIG_DECIMAL, nullable: true, icon: "far-tag" }),
            serial: property({ type: LONG, nullable: true, icon: "far-tag" }),
            expire: property({ type: ZonedDateTime.TYPE, nullable: true, icon: "far-tag" }),
        });
    }

    static readonly INSTANCE = new AbstractAssetTypeInfo();

}

export default AbstractAssetTypeInfo;