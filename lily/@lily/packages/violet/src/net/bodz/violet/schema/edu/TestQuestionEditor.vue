<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import TestQuestion from "./TestQuestion";
import { _TestQuestion_stuff_TYPE } from "./_TestQuestion_stuff_TypeInfo";

export const title = "Editor view of: Test question";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedEventFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedEventFieldGroup.vue";
import CoMessageFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoMessageFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import CourseChooseDialog from "./CourseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestQuestion>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestQuestion.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
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
        <CoImagedEventFieldGroup :meta="meta" v-model="model" />
        <CoMessageFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_TestQuestion_stuff_TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
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
            <FieldRow :property="meta.pos" v-model="model.pos">
                <input type="number" v-model="model.pos" />
            </FieldRow>
            <FieldRow :property="meta.answer" v-model="model.answer">
                <input type="text" v-model="model.answer" />
            </FieldRow>
            <FieldRow :property="meta.course" v-model="model.course">
                <RefEditor :dialog="courseChooseDialog" v-model="model.course" v-model:id="model.courseId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <CourseChooseDialog ref="courseChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
