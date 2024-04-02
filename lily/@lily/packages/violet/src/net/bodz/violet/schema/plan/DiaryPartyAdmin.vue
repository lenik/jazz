<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import DiaryParty from "./DiaryParty";
import { Diary_TYPE } from "./DiaryTypeInfo";

export const title = "Admin view of: Diary party";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import DiaryPartyEditor from "./DiaryPartyEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = DiaryParty.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "STRING": STRING,
    "Diary": Diary_TYPE,
    "User": User_TYPE,
    "Person": Person_TYPE,
    "Organization": Organization_TYPE,
    "INT": INT,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="Diary" data-format="label" data-field="diary">Diary</th>
            <th data-type="User" data-format="label" data-field="user">User</th>
            <th data-type="Person" data-format="label" data-field="person">Person</th>
            <th data-type="Organization" data-format="label" data-field="org">Org</th>
            <th data-type="INT" data-field="value">Value</th>
        </template>
        <template #preview>
            <DiaryPartyEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <DiaryPartyEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
