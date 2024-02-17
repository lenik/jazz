<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import GroupChooseDialog from "../account/GroupChooseDialog.vue";
import UserChooseDialog from "../account/UserChooseDialog.vue";
import FormDefChooseDialog from "../meta/FormDefChooseDialog.vue";
import type { Post } from "./Post";
import PostCategoryChooseDialog from "./PostCategoryChooseDialog.vue";
import PostChooseDialog from "./PostChooseDialog.vue";

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

const meta = Post.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const postChooseDialog = ref<InstanceType<typeof PostChooseDialog>>();
const postCategoryChooseDialog = ref<InstanceType<typeof PostCategoryChooseDialog>>();
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
            <FieldRow v-bind="fieldRowProps" :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUserId" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroupId" v-model:id="model.ownerGroupId" />
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
        <FieldGroup decl="net.bodz.lily.schema.pub._Post_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.favCount" v-model="model.favCount">
                <input type="number" v-model="model.favCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.voteCount" v-model="model.voteCount">
                <input type="number" v-model="model.voteCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.hateCount" v-model="model.hateCount">
                <input type="number" v-model="model.hateCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.messageCount" v-model="model.messageCount">
                <input type="number" v-model="model.messageCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.plugins" v-model="model.plugins">
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="postChooseDialog" v-model="model.parentId" v-model:id="model.parentId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.category" v-model="model.category">
                <RefEditor :dialog="postCategoryChooseDialog" v-model="model.categoryId" v-model:id="model.categoryId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub.Post">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
    <PostChooseDialog ref="postChooseDialog" />
    <PostCategoryChooseDialog ref="postCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
