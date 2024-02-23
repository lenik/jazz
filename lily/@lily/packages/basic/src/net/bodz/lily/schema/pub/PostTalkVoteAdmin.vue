<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer, long } from "@skeljs/core/src/lang/type";

import PostTalkVote from "./PostTalkVote";

export const title = "Admin view of: Post talk vote";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PostTalkVoteEditor from "./PostTalkVoteEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PostTalkVote.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="long" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="parent">Parent</th>
            <th data-type="string" data-format="label" data-field="user">User</th>
            <th data-type="integer" data-field="voteScore">Vote Score</th>
        </template>
        <template #preview>
            <PostTalkVoteEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PostTalkVoteEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
