<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import CourseKit from "./CourseKit";
import { _CourseKit_stuff_TYPE } from "./_CourseKit_stuff_TypeInfo";

export const title = "Editor view of: Course kit";
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

import CourseChooseDialog from "./CourseChooseDialog.vue";
import CourseKitCategoryChooseDialog from "./CourseKitCategoryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<CourseKit>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = CourseKit.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const courseKitCategoryChooseDialog = ref<InstanceType<typeof CourseKitCategoryChooseDialog>>();
const courseChooseDialog = ref<InstanceType<typeof CourseChooseDialog>>();
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
        <FieldGroup :type="_CourseKit_stuff_TYPE">
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
            <FieldRow :property="meta.favCount" v-model="model.favCount">
                <input type="number" v-model="model.favCount" />
            </FieldRow>
            <FieldRow :property="meta.voteCount" v-model="model.voteCount">
                <input type="number" v-model="model.voteCount" />
            </FieldRow>
            <FieldRow :property="meta.hateCount" v-model="model.hateCount">
                <input type="number" v-model="model.hateCount" />
            </FieldRow>
            <FieldRow :property="meta.dummy" v-model="model.dummy">
                <JsonEditor v-model="model.dummy" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="courseKitCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.course" v-model="model.course">
                <RefEditor :dialog="courseChooseDialog" v-model="model.course" v-model:id="model.courseId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <CourseKitCategoryChooseDialog ref="courseKitCategoryChooseDialog" />
    <CourseChooseDialog ref="courseChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
