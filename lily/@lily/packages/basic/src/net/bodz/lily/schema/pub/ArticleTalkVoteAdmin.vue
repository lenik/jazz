<script lang="ts">
import { onMounted, ref } from "vue";

import { INT } from "@skeljs/core/src/lang/baseinfo";

import Object from "../../../../../java/lang/Object";
import User from "../account/User";
import ArticleTalk from "./ArticleTalk";
import ArticleTalkVote from "./ArticleTalkVote";

export const title = "Admin view of: Article talk vote";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ArticleTalkVoteEditor from "./ArticleTalkVoteEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = ArticleTalkVote.TYPE;
const selection = ref<any>({});

const typeMap = {
    "Object": Object.TYPE,
    "ArticleTalk": ArticleTalk.TYPE,
    "User": User.TYPE,
    "INT": INT,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="Object" data-field="id">Id</th>
            <th data-type="ArticleTalk" data-format="label" data-field="parent">Parent</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
            <th data-type="INT" data-field="voteScore">Vote Score</th>
        </template>
        <template #preview>
            <ArticleTalkVoteEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArticleTalkVoteEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
