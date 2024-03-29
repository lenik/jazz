<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import Object from "../../../../../java/lang/Object";
import Group from "../account/Group";
import User from "../account/User";
import FormDef from "../meta/FormDef";
import Post from "./Post";
import PostCategory from "./PostCategory";

export const title = "Admin view of: Post";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PostEditor from "./PostEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Post.TYPE;
const selection = ref<any>({});

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

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
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
        </template>
        <template #preview>
            <PostEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
