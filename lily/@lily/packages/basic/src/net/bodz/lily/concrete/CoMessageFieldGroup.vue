<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import CoMessage from "./CoMessage";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import UserChooseDialog from "../schema/account/UserChooseDialog.vue";
import FormDefChooseDialog from "../schema/meta/FormDefChooseDialog.vue";

const model = defineModel<CoMessage<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoMessage.TYPE" ref="rootElement">
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
    <UserChooseDialog ref="userChooseDialog" />
    <FormDefChooseDialog ref="formDefChooseDialog" />
</template>

<style scoped lang="scss"></style>
