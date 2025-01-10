<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import TestQuestionTalk from "./TestQuestionTalk";
import { _TestQuestionTalk_stuff_TYPE } from "./_TestQuestionTalk_stuff_TypeInfo";

export const title = "Editor view of: Test question talk";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import UserChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/UserChooseDialog.vue";
import FormDefChooseDialog from "@lily/basic/src/net/bodz/lily/schema/meta/FormDefChooseDialog.vue";

import TestQuestionChooseDialog from "./TestQuestionChooseDialog.vue";
import TestQuestionTalkChooseDialog from "./TestQuestionTalkChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<TestQuestionTalk>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = TestQuestionTalk.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const testQuestionChooseDialog = ref<InstanceType<typeof TestQuestionChooseDialog>>();
const testQuestionTalkChooseDialog = ref<InstanceType<typeof TestQuestionTalkChooseDialog>>();
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
        <FieldGroup :type="_TestQuestionTalk_stuff_TYPE">
            <FieldRow :property="meta.beginTime" v-model="model.beginTime">
                <DateTime v-model="model.beginTime" />
            </FieldRow>
            <FieldRow :property="meta.endTime" v-model="model.endTime">
                <DateTime v-model="model.endTime" />
            </FieldRow>
            <FieldRow :property="meta.year" v-model="model.year">
                <input type="number" v-model="model.year" />
            </FieldRow>
            <FieldRow :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow :property="meta.rawText" v-model="model.rawText">
                <input type="text" v-model="model.rawText" />
            </FieldRow>
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.op" v-model="model.op">
                <RefEditor :dialog="userChooseDialog" v-model="model.op" v-model:id="model.opId" />
            </FieldRow>
            <FieldRow :property="meta.form" v-model="model.form">
                <RefEditor :dialog="formDefChooseDialog" v-model="model.form" v-model:id="model.formId" />
            </FieldRow>
            <FieldRow :property="meta.question" v-model="model.question">
                <RefEditor :dialog="testQuestionChooseDialog" v-model="model.question" v-model:id="model.questionId" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="testQuestionTalkChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
    <TestQuestionChooseDialog ref="testQuestionChooseDialog" />
    <TestQuestionTalkChooseDialog ref="testQuestionTalkChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
