<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import _ArtifactModel_stuff from "@lily/violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff";

import ArtifactModel from "./ArtifactModel";

export const title = "Editor view of: Artifact model";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoImagedEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ArtifactChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactChooseDialog.vue";
import ArtifactModelChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArtifactModel>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArtifactModel.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactModelChooseDialog = ref<InstanceType<typeof ArtifactModelChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_ArtifactModel_stuff.TYPE">
            <FieldRow :property="meta.valid" v-model="model.valid">
                <input type="checkbox" v-model="model.valid" />
            </FieldRow>
            <FieldRow :property="meta.validSince" v-model="model.validSince">
                <DateTime v-model="model.validSince" />
            </FieldRow>
            <FieldRow :property="meta.validUntil" v-model="model.validUntil">
                <DateTime v-model="model.validUntil" />
            </FieldRow>
            <FieldRow :property="meta.obsolete" v-model="model.obsolete">
                <RefEditor :dialog="artifactModelChooseDialog" v-model="model.obsolete" v-model:id="model.obsoleteId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="ArtifactModel.TYPE">
            <FieldRow :property="meta.modelName" v-model="model.modelName">
                <input type="text" v-model="model.modelName" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactModelChooseDialog ref="artifactModelChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
