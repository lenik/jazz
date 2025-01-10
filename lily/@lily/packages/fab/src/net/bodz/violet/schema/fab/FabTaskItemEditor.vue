<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabTaskItem from "./FabTaskItem";
import { _FabTaskItem_stuff_TYPE } from "./_FabTaskItem_stuff_TypeInfo";

export const title = "Editor view of: Fab task item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoEventFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoEventFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import ArtifactModelChooseDialog from "../art/ArtifactModelChooseDialog.vue";

import FabTaskChooseDialog from "./FabTaskChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabTaskItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabTaskItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabTaskChooseDialog = ref<InstanceType<typeof FabTaskChooseDialog>>();
const artifactModelChooseDialog = ref<InstanceType<typeof ArtifactModelChooseDialog>>();
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
        <CoEventFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_FabTaskItem_stuff_TYPE">
            <FieldRow :property="meta.deadline" v-model="model.deadline">
                <DateTime v-model="model.deadline" />
            </FieldRow>
            <FieldRow :property="meta.status" v-model="model.status">
                <input type="text" v-model="model.status" />
            </FieldRow>
            <FieldRow :property="meta.quantity" v-model="model.quantity">
                <input type="number" v-model="model.quantity" />
            </FieldRow>
            <FieldRow :property="meta.batch" v-model="model.batch">
                <JsonEditor v-model="model.batch" />
            </FieldRow>
            <FieldRow :property="meta.trackCount" v-model="model.trackCount">
                <input type="number" v-model="model.trackCount" />
            </FieldRow>
            <FieldRow :property="meta.task" v-model="model.task">
                <RefEditor :dialog="fabTaskChooseDialog" v-model="model.task" v-model:id="model.taskId" />
            </FieldRow>
            <FieldRow :property="meta.model" v-model="model.model">
                <RefEditor :dialog="artifactModelChooseDialog" v-model="model.model" v-model:id="model.modelId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabTaskChooseDialog ref="fabTaskChooseDialog" />
    <ArtifactModelChooseDialog ref="artifactModelChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
