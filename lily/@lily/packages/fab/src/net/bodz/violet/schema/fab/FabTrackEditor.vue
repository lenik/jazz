<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";
import CoObject from "@lily/basic/src/net/bodz/lily/concrete/CoObject";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import StructRow from "@lily/basic/src/net/bodz/lily/concrete/StructRow";

import FabTrack from "./FabTrack";
import _FabTrack_stuff from "./_FabTrack_stuff";

export const title = "Editor view of: Fab track";
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
import OrgUnitChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitChooseDialog.vue";

import FabProcessChooseDialog from "./FabProcessChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabTrack>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabTrack.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const fabProcessChooseDialog = ref<InstanceType<typeof FabProcessChooseDialog>>();
const orgUnitChooseDialog = ref<InstanceType<typeof OrgUnitChooseDialog>>();
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
            <FieldRow :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow :property="meta.icon" v-model="model.icon">
                <input type="text" v-model="model.icon" />
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
        <FieldGroup :type="_FabTrack_stuff.TYPE">
            <FieldRow :property="meta.since" v-model="model.since">
                <DateTime v-model="model.since" />
            </FieldRow>
            <FieldRow :property="meta.deadline" v-model="model.deadline">
                <DateTime v-model="model.deadline" />
            </FieldRow>
            <FieldRow :property="meta.plannedQuantity" v-model="model.plannedQuantity">
                <input type="number" v-model="model.plannedQuantity" />
            </FieldRow>
            <FieldRow :property="meta.actualQuantity" v-model="model.actualQuantity">
                <input type="number" v-model="model.actualQuantity" />
            </FieldRow>
            <FieldRow :property="meta.validQuantity" v-model="model.validQuantity">
                <input type="number" v-model="model.validQuantity" />
            </FieldRow>
            <FieldRow :property="meta.process" v-model="model.process">
                <RefEditor :dialog="fabProcessChooseDialog" v-model="model.process" v-model:id="model.processId" />
            </FieldRow>
            <FieldRow :property="meta.orgUnit" v-model="model.orgUnit">
                <RefEditor :dialog="orgUnitChooseDialog" v-model="model.orgUnit" v-model:id="model.orgUnitId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <FabProcessChooseDialog ref="fabProcessChooseDialog" />
    <OrgUnitChooseDialog ref="orgUnitChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
