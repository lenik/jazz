<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";
import CoObject from "@lily/basic/src/net/bodz/lily/concrete/CoObject";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import StructRow from "@lily/basic/src/net/bodz/lily/concrete/StructRow";

import FabOrder from "./FabOrder";
import _FabOrder_stuff from "./_FabOrder_stuff";

export const title = "Editor view of: Fab order";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import GroupChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/GroupChooseDialog.vue";
import UserChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/UserChooseDialog.vue";
import OrganizationChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";
import FormDefChooseDialog from "@lily/basic/src/net/bodz/lily/schema/meta/FormDefChooseDialog.vue";
import PlanChooseDialog from "@lily/violet/src/net/bodz/violet/schema/plan/PlanChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabOrder>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabOrder.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const planChooseDialog = ref<InstanceType<typeof PlanChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow :property="meta.creationDate" v-model="model.creationDate">
                <DateTime v-model="model.creationDate" />
            </FieldRow>
            <FieldRow :property="meta.lastModified" v-model="model.lastModified">
                <DateTime v-model="model.lastModified" />
            </FieldRow>
            <FieldRow :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoObject.TYPE">
            <FieldRow :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
            </FieldRow>
            <FieldRow :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
            <FieldRow :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUser" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroup" v-model:id="model.ownerGroupId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoEvent.TYPE">
            <FieldRow :property="meta.beginTime" v-model="model.beginTime">
                <DateTime v-model="model.beginTime" />
            </FieldRow>
            <FieldRow :property="meta.endTime" v-model="model.endTime">
                <DateTime v-model="model.endTime" />
            </FieldRow>
            <FieldRow :property="meta.year" v-model="model.year">
                <input type="number" v-model="model.year" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoMessage.TYPE">
            <FieldRow :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow :property="meta.rawText" v-model="model.rawText">
                <input type="text" v-model="model.rawText" />
            </FieldRow>
            <FieldRow :property="meta.op" v-model="model.op">
                <RefEditor :dialog="userChooseDialog" v-model="model.op" v-model:id="model.opId" />
            </FieldRow>
            <FieldRow :property="meta.form" v-model="model.form">
                <RefEditor :dialog="formDefChooseDialog" v-model="model.form" v-model:id="model.formId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabOrder_stuff.TYPE">
            <FieldRow :property="meta.formArguments" v-model="model.formArguments">
                <input type="text" v-model="model.formArguments" />
            </FieldRow>
            <FieldRow :property="meta.nitem" v-model="model.nitem">
                <input type="number" v-model="model.nitem" />
            </FieldRow>
            <FieldRow :property="meta.totalQuantity" v-model="model.totalQuantity">
                <input type="number" v-model="model.totalQuantity" />
            </FieldRow>
            <FieldRow :property="meta.totalAmount" v-model="model.totalAmount">
                <input type="number" v-model="model.totalAmount" />
            </FieldRow>
            <FieldRow :property="meta.taskCount" v-model="model.taskCount">
                <input type="number" v-model="model.taskCount" />
            </FieldRow>
            <FieldRow :property="meta.processCount" v-model="model.processCount">
                <input type="number" v-model="model.processCount" />
            </FieldRow>
            <FieldRow :property="meta.trackCount" v-model="model.trackCount">
                <input type="number" v-model="model.trackCount" />
            </FieldRow>
            <FieldRow :property="meta.plan" v-model="model.plan">
                <RefEditor :dialog="planChooseDialog" v-model="model.plan" v-model:id="model.planId" />
            </FieldRow>
            <FieldRow :property="meta.customOrg" v-model="model.customOrg">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.customOrg" v-model:id="model.customOrgId" />
            </FieldRow>
            <FieldRow :property="meta.custom" v-model="model.custom">
                <RefEditor :dialog="personChooseDialog" v-model="model.custom" v-model:id="model.customId" />
            </FieldRow>
            <FieldRow :property="meta.clerk" v-model="model.clerk">
                <RefEditor :dialog="personChooseDialog" v-model="model.clerk" v-model:id="model.clerkId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
    <PlanChooseDialog ref="planChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
