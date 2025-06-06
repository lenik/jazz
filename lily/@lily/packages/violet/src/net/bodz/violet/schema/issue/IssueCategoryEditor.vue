<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { CoNode_TYPE } from "lily-basic/src/net/bodz/lily/concrete/CoNodeTypeInfo";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import IssueCategory from "./IssueCategory";
import { _IssueCategory_stuff_TYPE } from "./_IssueCategory_stuff_TypeInfo";

export const title = "Editor view of: Issue category";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import IssueCategoryChooseDialog from "./IssueCategoryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<IssueCategory>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = IssueCategory.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const issueCategoryChooseDialog = ref<InstanceType<typeof IssueCategoryChooseDialog>>();
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
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="CoNode_TYPE">
            <FieldRow :property="meta.refCount" v-model="model.refCount">
                <input type="number" v-model="model.refCount" disabled />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="issueCategoryChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_IssueCategory_stuff_TYPE">
            <FieldRow :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
        </FieldGroup>
    </div>
    <IssueCategoryChooseDialog ref="issueCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
