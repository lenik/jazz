<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { CoParameter_TYPE } from "./CoParameterTypeInfo";
import CoParameter from "./CoParameter";
import UomRowChooseDialog from "../schema/util/UomRowChooseDialog.vue";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";

const model = defineModel<CoParameter<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const uomRowChooseDialog = ref<InstanceType<typeof UomRowChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoParameter_TYPE" ref="rootElement">
        <FieldRow :property="meta.name" v-model="model.name">
            <input type="text" v-model="model.name" />
        </FieldRow>
        <FieldRow :property="meta.type" v-model="model.type">
            <input type="text" v-model="model.type" />
        </FieldRow>
        <FieldRow :property="meta.optional" v-model="model.optional">
            <input type="checkbox" v-model="model.optional" /> Optional
        </FieldRow>
        <FieldRow :property="meta.uom" v-model="model.uom">
            <RefEditor :dialog="uomRowChooseDialog" v-model="model.uom" v-model:id="model.uomId" />
        </FieldRow>
        <FieldRow :property="meta.values" v-model="model.values">
            <input type="text" v-model="model.values" />
        </FieldRow>
    </FieldGroup>
    <uomRowChooseDialog ref="uomRowChooseDialog" />
</template>

<style scoped lang="scss"></style>
