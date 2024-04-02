<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import PlanFav from "./PlanFav";
import { Plan_TYPE } from "./PlanTypeInfo";

export const title = "Admin view of: Plan fav";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PlanFavEditor from "./PlanFavEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PlanFav.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "Plan": Plan_TYPE,
    "User": User_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="Plan" data-format="label" data-field="plan">Plan</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
        </template>
        <template #preview>
            <PlanFavEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PlanFavEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
