<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import UserChooseDialog from "../account/UserChooseDialog.vue";
import FormDefChooseDialog from "../meta/FormDefChooseDialog.vue";
import ArticleChooseDialog from "./ArticleChooseDialog.vue";
import type { ArticleTalk } from "./ArticleTalk";
import ArticleTalkChooseDialog from "./ArticleTalkChooseDialog.vue";

export interface Props {
}
</script>

<script setup lang="ts">
defineOptions({
    inheritAttrs: false
});

const model = defineModel<%s>();Person

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleTalk.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const articleTalkChooseDialog = ref<InstanceType<typeof ArticleTalkChooseDialog>>();
const articleChooseDialog = ref<InstanceType<typeof ArticleChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
            <FieldRow v-bind="fieldRowProps" :property="meta.creationDate" v-model="model.creationDate">
                <input type="date" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="date" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
            <FieldRow v-bind="fieldRowProps" :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.IdEntity">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoMomentInterval">
            <FieldRow v-bind="fieldRowProps" :property="meta.beginTime" v-model="model.beginTime">
                <input type="date" v-model="model.beginTime" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.endTime" v-model="model.endTime">
                <input type="date" v-model="model.endTime" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.year" v-model="model.year">
                <input type="number" v-model="model.year" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoMessage">
            <FieldRow v-bind="fieldRowProps" :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.rawText" v-model="model.rawText">
                <input type="text" v-model="model.rawText" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.op" v-model="model.op">
                <RefEditor :dialog="userChooseDialog" v-model="model.opId" v-model:id="model.opId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.form" v-model="model.form">
                <RefEditor :dialog="formDefChooseDialog" v-model="model.formId" v-model:id="model.formId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoTalk">
            <FieldRow v-bind="fieldRowProps" :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="articleTalkChooseDialog" v-model="model.parentId" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub._ArticleTalk_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.article" v-model="model.article">
                <RefEditor :dialog="articleChooseDialog" v-model="model.articleId" v-model:id="model.articleId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub.ArticleTalk">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
    <ArticleTalkChooseDialog ref="articleTalkChooseDialog" />
    <ArticleChooseDialog ref="articleChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
