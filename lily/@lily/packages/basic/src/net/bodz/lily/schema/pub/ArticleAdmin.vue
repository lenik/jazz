<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer, long } from "@skeljs/core/src/lang/type";

import Article from "./Article";

export const title = "Admin view of: Article";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ArticleEditor from "./ArticleEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Article.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="long" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="string" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="integer" data-field="accessMode">Access Mode</th>
            <th data-type="integer" data-field="acl">Acl</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="Moment" data-field="beginTime">Begin Time</th>
            <th data-type="Moment" data-field="endTime">End Time</th>
            <th data-type="integer" data-field="year">Year</th>
            <th data-type="string" data-field="subject">Subject</th>
            <th data-type="string" data-format="label" data-field="op">Op</th>
            <th data-type="string" data-field="rawText">Raw Text</th>
            <th data-type="string" data-format="label" data-field="form">Form</th>
            <th data-type="string" data-field="formArguments">Form Arguments</th>
            <th data-type="string" data-format="label" data-field="category">Category</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="integer" data-field="favCount">Fav Count</th>
            <th data-type="integer" data-field="voteCount">Vote Count</th>
            <th data-type="integer" data-field="hateCount">Hate Count</th>
            <th data-type="integer" data-field="messageCount">Message Count</th>
            <th data-type="any" data-field="plugins">Plugins</th>
        </template>
        <template #preview>
            <ArticleEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArticleEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
