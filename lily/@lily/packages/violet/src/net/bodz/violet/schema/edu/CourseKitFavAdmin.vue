<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "skel01-core/src/lang/baseinfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import CourseKitFav from "./CourseKitFav";
import { CourseKit_TYPE } from "./CourseKitTypeInfo";

export const title = "Admin view of: Course kit fav";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import CourseKitFavEditor from "./CourseKitFavEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = CourseKitFav.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "CourseKit": CourseKit_TYPE,
    "User": User_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="CourseKit" data-format="label" data-field="coursekit">Coursekit</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
        </template>
        <template #preview>
            <CourseKitFavEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <CourseKitFavEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
