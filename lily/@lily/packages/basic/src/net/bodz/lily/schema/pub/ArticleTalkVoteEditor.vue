<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer, long } from "@skeljs/core/src/lang/type";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import IdEntity from "../../concrete/IdEntity";
import VoteRecord from "../../concrete/VoteRecord";
import ArticleTalkVote from "./ArticleTalkVote";
import _ArticleTalkVote_stuff from "./_ArticleTalkVote_stuff";

export const title = "Editor view of: Article talk vote";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import UserChooseDialog from "../account/UserChooseDialog.vue";
import ArticleTalkChooseDialog from "./ArticleTalkChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArticleTalkVote>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleTalkVote.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const articleTalkChooseDialog = ref<InstanceType<typeof ArticleTalkChooseDialog>>();
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="VoteRecord.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArticleTalkVote_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.voteScore" v-model="model.voteScore">
                <input type="number" v-model="model.voteScore" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="articleTalkChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <ArticleTalkChooseDialog ref="articleTalkChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
