<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import ArtifactType from "./ArtifactType";
import { _ArtifactType_stuff_TYPE } from "./_ArtifactType_stuff_TypeInfo";

export const title = "Editor view of: Artifact type";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import UomRowChooseDialog from "lily-basic/src/net/bodz/lily/schema/util/UomRowChooseDialog.vue";

import ArtifactCategoryChooseDialog from "./ArtifactCategoryChooseDialog.vue";
import ArtifactTypeChooseDialog from "./ArtifactTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArtifactType>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArtifactType.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactCategoryChooseDialog = ref<InstanceType<typeof ArtifactCategoryChooseDialog>>();
const artifactTypeChooseDialog = ref<InstanceType<typeof ArtifactTypeChooseDialog>>();
const uomRowChooseDialog = ref<InstanceType<typeof UomRowChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArtifactType_stuff_TYPE">
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="artifactCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="artifactTypeChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
            <FieldRow :property="meta.uom" v-model="model.uom">
                <RefEditor :dialog="uomRowChooseDialog" v-model="model.uom" v-model:id="model.uomId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactCategoryChooseDialog ref="artifactCategoryChooseDialog" />
    <ArtifactTypeChooseDialog ref="artifactTypeChooseDialog" />
    <UomRowChooseDialog ref="uomRowChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
