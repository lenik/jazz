<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import Object from "../../../../../java/lang/Object";
import Group from "../account/Group";
import User from "../account/User";
import FormDef from "../meta/FormDef";
import Post from "./Post";
import { Post } from "./Post";
import PostCategory from "./PostCategory";

export const title = "Choose dialog for: Post";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "Object": Object.TYPE,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "int": int.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef.TYPE,
    "Post": Post.TYPE,
    "PostCategory": PostCategory.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
};

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Post.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="Object" data-field="id">Id</th>
        <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="INT" data-field="accessMode">Access Mode</th>
        <th data-type="INT" data-field="acl">Acl</th>
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
        <th data-type="Post" data-format="label" data-field="parent">Parent</th>
        <th data-type="PostCategory" data-format="label" data-field="category">Category</th>
        <th data-type="INT" data-field="favCount">Fav Count</th>
        <th data-type="INT" data-field="voteCount">Vote Count</th>
        <th data-type="INT" data-field="hateCount">Hate Count</th>
        <th data-type="INT" data-field="messageCount">Message Count</th>
        <th data-type="JSON_VARIANT" data-field="plugins">Plugins</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
