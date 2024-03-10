<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { BigDecimal, int, long } from "@skeljs/core/src/lang/basetype";
import type { Timestamp } from "@skeljs/core/src/lang/time";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import CoEvent from "@lily/basic/src/net/bodz/lily/concrete/CoEvent";
import CoImagedEvent from "@lily/basic/src/net/bodz/lily/concrete/CoImagedEvent";
import CoObject from "@lily/basic/src/net/bodz/lily/concrete/CoObject";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";
import StructRow from "@lily/basic/src/net/bodz/lily/concrete/StructRow";

import FabOrderItem from "./FabOrderItem";
import _FabOrderItem_stuff from "./_FabOrderItem_stuff";

export const title = "Editor view of: Fab order item";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import ArtifactChooseDialog from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactChooseDialog.vue";

import FabOrderChooseDialog from "./FabOrderChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabOrderItem>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabOrderItem.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabOrderChooseDialog = ref<InstanceType<typeof FabOrderChooseDialog>>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
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
            <FieldRow :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
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
        <FieldGroup :type="CoImagedEvent.TYPE">
            <FieldRow :property="meta.properties" v-model="model.properties">
                <JsonEditor v-model="model.properties" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabOrderItem_stuff.TYPE">
            <FieldRow :property="meta.resale" v-model="model.resale">
                <input type="checkbox" v-model="model.resale" />
            </FieldRow>
            <FieldRow :property="meta.altLabel" v-model="model.altLabel">
                <input type="text" v-model="model.altLabel" />
            </FieldRow>
            <FieldRow :property="meta.altSpec" v-model="model.altSpec">
                <input type="text" v-model="model.altSpec" />
            </FieldRow>
            <FieldRow :property="meta.altUom" v-model="model.altUom">
                <input type="text" v-model="model.altUom" />
            </FieldRow>
            <FieldRow :property="meta.quantity" v-model="model.quantity">
                <input type="number" v-model="model.quantity" />
            </FieldRow>
            <FieldRow :property="meta.price" v-model="model.price">
                <input type="number" v-model="model.price" />
            </FieldRow>
            <FieldRow :property="meta.amount" v-model="model.amount">
                <input type="number" v-model="model.amount" />
            </FieldRow>
            <FieldRow :property="meta.notes" v-model="model.notes">
                <input type="text" v-model="model.notes" />
            </FieldRow>
            <FieldRow :property="meta.order" v-model="model.order">
                <RefEditor :dialog="fabOrderChooseDialog" v-model="model.order" v-model:id="model.orderId" />
            </FieldRow>
            <FieldRow :property="meta.artifact" v-model="model.artifact">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.artifact" v-model:id="model.artifactId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabOrderChooseDialog ref="fabOrderChooseDialog" />
    <ArtifactChooseDialog ref="artifactChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
