<script lang="ts">
import { onMounted, ref } from "vue";

import { LONG, STRING } from "skel01-core/src/lang/baseinfo";
import { Organization_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import PlanParty from "./PlanParty";
import { Plan_TYPE } from "./PlanTypeInfo";

export const title = "Admin view of: Plan party";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import PlanPartyEditor from "./PlanPartyEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PlanParty.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "Plan": Plan_TYPE,
    "Person": Person_TYPE,
    "Organization": Organization_TYPE,
    "STRING": STRING,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="Plan" data-format="label" data-field="plan">Plan</th>
            <th data-type="Person" data-format="label" data-field="person">Person</th>
            <th data-type="Organization" data-format="label" data-field="org">Org</th>
            <th data-type="STRING" data-field="description">Description</th>
        </template>
        <template #preview>
            <PlanPartyEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PlanPartyEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
