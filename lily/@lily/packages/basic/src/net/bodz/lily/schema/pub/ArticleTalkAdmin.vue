<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import Object from "../../../../../java/lang/Object";
import User from "../account/User";
import FormDef from "../meta/FormDef";
import Article from "./Article";
import ArticleTalk from "./ArticleTalk";

export const title = "Admin view of: Article talk";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ArticleTalkEditor from "./ArticleTalkEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = ArticleTalk.TYPE;
const selection = ref<any>({});

const typeMap = {
    "Object": Object.TYPE,
    "INT": INT,
    "int": int.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "STRING": STRING,
    "User": User.TYPE,
    "FormDef": FormDef.TYPE,
    "Article": Article.TYPE,
    "ArticleTalk": ArticleTalk.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="Object" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="int" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="ZonedDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="ZonedDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="User" data-format="label" data-field="op">Op</th>
            <th data-type="STRING" data-field="rawText">Raw Text</th>
            <th data-type="FormDef" data-format="label" data-field="form">Form</th>
            <th data-type="STRING" data-field="formArguments">Form Arguments</th>
            <th data-type="Article" data-format="label" data-field="article">Article</th>
            <th data-type="ArticleTalk" data-format="label" data-field="parent">Parent</th>
        </template>
        <template #preview>
            <ArticleTalkEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArticleTalkEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
