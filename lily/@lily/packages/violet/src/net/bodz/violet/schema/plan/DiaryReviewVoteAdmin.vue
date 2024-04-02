<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import { DiaryReview_TYPE } from "./DiaryReviewTypeInfo";
import DiaryReviewVote from "./DiaryReviewVote";

export const title = "Admin view of: Diary review vote";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import DiaryReviewVoteEditor from "./DiaryReviewVoteEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = DiaryReviewVote.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "DiaryReview": DiaryReview_TYPE,
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
            <th data-type="DiaryReview" data-format="label" data-field="parent">Parent</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
            <th data-type="INT" data-field="voteScore">Vote Score</th>
        </template>
        <template #preview>
            <DiaryReviewVoteEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <DiaryReviewVoteEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
