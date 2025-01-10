<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import DiaryReviewVote from "./DiaryReviewVote";
import { _DiaryReviewVote_stuff_TYPE } from "./_DiaryReviewVote_stuff_TypeInfo";

export const title = "Editor view of: Diary review vote";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import VoteRecordFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordFieldGroup.vue";

import DiaryReviewChooseDialog from "./DiaryReviewChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<DiaryReviewVote>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = DiaryReviewVote.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const diaryReviewChooseDialog = ref<InstanceType<typeof DiaryReviewChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <VoteRecordFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_DiaryReviewVote_stuff_TYPE">
            <FieldRow :property="meta.voteScore" v-model="model.voteScore">
                <input type="number" v-model="model.voteScore" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="diaryReviewChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <DiaryReviewChooseDialog ref="diaryReviewChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
