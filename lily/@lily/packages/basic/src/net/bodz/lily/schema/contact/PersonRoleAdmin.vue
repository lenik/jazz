<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "skel01-core/src/lang/baseinfo";

import { OrgUnit_TYPE } from "./OrgUnitTypeInfo";
import { Organization_TYPE } from "./OrganizationTypeInfo";
import PersonRole from "./PersonRole";
import { Person_TYPE } from "./PersonTypeInfo";

export const title = "Admin view of: Person role";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import PersonRoleEditor from "./PersonRoleEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PersonRole.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "Organization": Organization_TYPE,
    "OrgUnit": OrgUnit_TYPE,
    "Person": Person_TYPE,
    "STRING": STRING,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="Organization" data-format="label" data-field="org">Org</th>
            <th data-type="OrgUnit" data-format="label" data-field="orgUnit">Org Unit</th>
            <th data-type="Person" data-format="label" data-field="person">Person</th>
            <th data-type="STRING" data-field="role">Role</th>
            <th data-type="STRING" data-field="description">Description</th>
        </template>
        <template #preview>
            <PersonRoleEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PersonRoleEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
