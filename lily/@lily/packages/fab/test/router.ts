import { createRouter, createWebHashHistory } from 'vue-router';

export const links = {
    "": "component ",
    "Project": "component Project",
    "ArtifactModelAdmin": "Admin view of: Artifact model",
    "FabFnAdmin": "Admin view of: Fab fn",
    "FabOrderAdmin": "Admin view of: Fab order",
    "FabOrderItemAdmin": "Admin view of: Fab order item",
    "FabProcessAdmin": "Admin view of: Fab process",
    "FabProcessSerialAdmin": "Admin view of: Fab process serial",
    "FabStdProcessAdmin": "Admin view of: Fab std process",
    "FabStdProcessInputAdmin": "Admin view of: Fab std process input",
    "FabStdTestAdmin": "Admin view of: Fab std test",
    "FabStdTestCategoryAdmin": "Admin view of: Fab std test category",
    "FabStdTestParameterAdmin": "Admin view of: Fab std test parameter",
    "FabStdTesterAdmin": "Admin view of: Fab std tester",
    "FabTaskAdmin": "Admin view of: Fab task",
    "FabTaskItemAdmin": "Admin view of: Fab task item",
    "FabTrackAdmin": "Admin view of: Fab track",
    "FabTrackPartyAdmin": "Admin view of: Fab track party",
    "FabTrackTestAdmin": "Admin view of: Fab track test",
    "FabTrackTestParameterAdmin": "Admin view of: Fab track test parameter",
};

export const routes = [
    { path: '/', component: () => import('./Index.vue') },
    { path: '/Project', component: () => import('skel01-core/src/ui/demo/Project.vue') },
    { path: '/ArtifactModelAdmin', component: () => import('../src/net/bodz/violet/schema/art/ArtifactModelAdmin.vue') },
    { path: '/FabFnAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabFnAdmin.vue') },
    { path: '/FabOrderAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabOrderAdmin.vue') },
    { path: '/FabOrderItemAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabOrderItemAdmin.vue') },
    { path: '/FabProcessAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabProcessAdmin.vue') },
    { path: '/FabProcessSerialAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabProcessSerialAdmin.vue') },
    { path: '/FabStdProcessAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdProcessAdmin.vue') },
    { path: '/FabStdProcessInputAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdProcessInputAdmin.vue') },
    { path: '/FabStdTestAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdTestAdmin.vue') },
    { path: '/FabStdTestCategoryAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdTestCategoryAdmin.vue') },
    { path: '/FabStdTestParameterAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdTestParameterAdmin.vue') },
    { path: '/FabStdTesterAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabStdTesterAdmin.vue') },
    { path: '/FabTaskAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTaskAdmin.vue') },
    { path: '/FabTaskItemAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTaskItemAdmin.vue') },
    { path: '/FabTrackAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTrackAdmin.vue') },
    { path: '/FabTrackPartyAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTrackPartyAdmin.vue') },
    { path: '/FabTrackTestAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTrackTestAdmin.vue') },
    { path: '/FabTrackTestParameterAdmin', component: () => import('../src/net/bodz/violet/schema/fab/FabTrackTestParameterAdmin.vue') },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
});

export default router;
