<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "skel01-core/src/lang/baseinfo";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import { CourseKitFav } from "./CourseKitFav";
import { CourseKit_TYPE } from "./CourseKitTypeInfo";

export const title = "Choose dialog for: Course kit fav";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "LONG": LONG,
    "CourseKit": CourseKit_TYPE,
    "User": User_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="CourseKitFav.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="CourseKit" data-format="label" data-field="coursekit">Coursekit</th>
        <th data-type="User" data-format="label" data-field="user">User</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
