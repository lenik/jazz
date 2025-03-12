import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { INT, LIST, UNDEFINED } from 'skel01-core/src/lang/baseinfo';
import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import CoImagedValidators from './CoImagedValidators';
import Attachment from 'skel01-core/src/net/bodz/lily/entity/Attachment';

export class CoImagedTypeInfo extends IdEntityTypeInfo {

    readonly validators = new CoImagedValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoImaged"; }
    get icon() { return "fa-image"; }
    get label() { return "Concrete Entity With Image"; }
    get description() { return "An id-entity with optional one or many images."; }

    override preamble() {
        super.preamble();
        this.declare({
            images: property({
                type: LIST(Attachment.TYPE), nullable: true,
                icon: "far-images",
                validator: this.validators.validateImages,
            }),
            image: property({
                type: Attachment.TYPE, nullable: true,
                icon: "far-image",
                validator: this.validators.validateImage,
            }),
            videos: property({
                type: LIST(Attachment.TYPE), nullable: true,
                icon: "far-photo-video",
                validator: this.validators.validateVideos,
            }),
            video: property({
                type: Attachment.TYPE, nullable: true,
                icon: "far-film",
                validator: this.validators.validateVideo,
            }),
        });
    }

    static readonly INSTANCE = new CoImagedTypeInfo(UNDEFINED);

}

export default CoImagedTypeInfo;
export const CoImaged_TYPE = CoImagedTypeInfo.INSTANCE;
