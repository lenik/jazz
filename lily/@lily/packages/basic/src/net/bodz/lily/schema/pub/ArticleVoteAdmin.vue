<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG } from "skel01-core/src/lang/baseinfo";

import { User_TYPE } from "../account/UserTypeInfo";
import { Article_TYPE } from "./ArticleTypeInfo";
import ArticleVote from "./ArticleVote";

export const title = "Admin view of: Article vote";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import ArticleVoteEditor from "./ArticleVoteEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = ArticleVote.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "Article": Article_TYPE,
    "User": User_TYPE,
    "INT": INT,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="Article" data-format="label" data-field="parent">Parent</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
            <th data-type="INT" data-field="voteScore">Vote Score</th>
        </template>
        <template #preview>
            <ArticleVoteEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArticleVoteEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
