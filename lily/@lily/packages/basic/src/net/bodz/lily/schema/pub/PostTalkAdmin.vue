<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import { User_TYPE } from "../account/UserTypeInfo";
import { FormDef_TYPE } from "../meta/FormDefTypeInfo";
import PostTalk from "./PostTalk";
import { PostTalk_TYPE } from "./PostTalkTypeInfo";
import { Post_TYPE } from "./PostTypeInfo";

export const title = "Admin view of: Post talk";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PostTalkEditor from "./PostTalkEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostTalk.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "STRING": STRING,
    "User": User_TYPE,
    "FormDef": FormDef_TYPE,
    "Post": Post_TYPE,
    "PostTalk": PostTalk_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="User" data-format="label" data-field="op">Op</th>
            <th data-type="STRING" data-field="rawText">Raw Text</th>
            <th data-type="FormDef" data-format="label" data-field="form">Form</th>
            <th data-type="STRING" data-field="formArguments">Form Arguments</th>
            <th data-type="Post" data-format="label" data-field="post">Post</th>
            <th data-type="PostTalk" data-format="label" data-field="parent">Parent</th>
        </template>
        <template #preview>
            <PostTalkEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostTalkEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
