<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "@skeljs/core/src/lang/baseinfo";

import { User_TYPE } from "../account/UserTypeInfo";
import ArticleFav from "./ArticleFav";
import { Article_TYPE } from "./ArticleTypeInfo";

export const title = "Admin view of: Article fav";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ArticleFavEditor from "./ArticleFavEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = ArticleFav.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "Article": Article_TYPE,
    "User": User_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="Article" data-format="label" data-field="article">Article</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
        </template>
        <template #preview>
            <ArticleFavEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ArticleFavEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
