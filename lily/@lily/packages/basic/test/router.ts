import { createRouter, createWebHashHistory } from 'vue-router';

export const links = {
    "": "component ",
    "MyPersonAdmin": '[My] Person Admin',
    "MyPersonListing": '[My] Person Listing',
    "PersonChooseDialog": "component PersonChooseDialog",
    "PersonEditor": "component PersonEditor",
    "Project": "component Project",
    "GroupAdmin": "Admin view of: Group",
    "GroupTypeAdmin": "Admin view of: Group type",
    "PolicyAdmin": "Admin view of: Policy",
    "UserAdmin": "Admin view of: User",
    "UserOtherIdAdmin": "Admin view of: User other id",
    "UserOtherIdTypeAdmin": "Admin view of: User other id type",
    "UserRunAdmin": "Admin view of: User run",
    "UserSecretAdmin": "Admin view of: User secret",
    "UserTypeAdmin": "Admin view of: User type",
    "OrgUnitAdmin": "Admin view of: Org unit",
    "OrganizationAdmin": "Admin view of: Organization",
    "PartyCategoryAdmin": "Admin view of: Party category",
    "PersonAdmin": "Admin view of: Person",
    "PersonRoleAdmin": "Admin view of: Person role",
    "PersonTagAdmin": "Admin view of: Person tag",
    "PersonTagTypeAdmin": "Admin view of: Person tag type",
    "ZoneAdmin": "Admin view of: Zone",
    "ZoneCategoryAdmin": "Admin view of: Zone category",
    "ExternalSiteAdmin": "Admin view of: External site",
    "StorageAdmin": "Admin view of: Storage",
    "CategoryDefAdmin": "Admin view of: Category def",
    "FormDefAdmin": "Admin view of: Form def",
    "FormParameterAdmin": "Admin view of: Form parameter",
    "ParameterDefAdmin": "Admin view of: Parameter def",
    "ParameterValueAdmin": "Admin view of: Parameter value",
    "PhaseDefAdmin": "Admin view of: Phase def",
    "PriorityDefAdmin": "Admin view of: Priority def",
    "SchemaDefAdmin": "Admin view of: Schema def",
    "TagDefAdmin": "Admin view of: Tag def",
    "TagGroupDefAdmin": "Admin view of: Tag group def",
    "ArticleAdmin": "Admin view of: Article",
    "ArticleBackrefAdmin": "Admin view of: Article backref",
    "ArticleCategoryAdmin": "Admin view of: Article category",
    "ArticleFavAdmin": "Admin view of: Article fav",
    "ArticleParameterAdmin": "Admin view of: Article parameter",
    "ArticleParameterTypeAdmin": "Admin view of: Article parameter type",
    "ArticleTagAdmin": "Admin view of: Article tag",
    "ArticleTagTypeAdmin": "Admin view of: Article tag type",
    "ArticleTalkAdmin": "Admin view of: Article talk",
    "ArticleTalkVoteAdmin": "Admin view of: Article talk vote",
    "ArticleVoteAdmin": "Admin view of: Article vote",
    "PostAdmin": "Admin view of: Post",
    "PostBackrefAdmin": "Admin view of: Post backref",
    "PostCategoryAdmin": "Admin view of: Post category",
    "PostFavAdmin": "Admin view of: Post fav",
    "PostParameterAdmin": "Admin view of: Post parameter",
    "PostParameterTypeAdmin": "Admin view of: Post parameter type",
    "PostTagAdmin": "Admin view of: Post tag",
    "PostTagTypeAdmin": "Admin view of: Post tag type",
    "PostTalkAdmin": "Admin view of: Post talk",
    "PostTalkVoteAdmin": "Admin view of: Post talk vote",
    "PostVoteAdmin": "Admin view of: Post vote",
    "BadgeAdmin": "Admin view of: Badge",
    "UserBadgeAdmin": "Admin view of: User badge",
    "UomAdmin": "Admin view of: Uom",
    "UomRowAdmin": "Admin view of: Uom row",
    "ApiTypeAdmin": "Admin view of: Api type",
    "VApiAdmin": "Admin view of: V api",
    "VApiCreditAdmin": "Admin view of: V api credit",
    "VApiLogAdmin": "Admin view of: V api log",
    "VAppAdmin": "Admin view of: V app",
    "VAppCategoryAdmin": "Admin view of: V app category",
    "VAppRequestAdmin": "Admin view of: V app request",
    "VAppRequestApiAdmin": "Admin view of: V app request api",
};

export const routes = [
    { path: '/', component: () => import('./Index.vue') },
    { path: '/MyPersonAdmin', component: () => import('./contact/MyPersonAdmin.vue') },
    { path: '/MyPersonListing', component: () => import('./contact/MyPersonListing.vue') },
    { path: '/PersonChooseDialog', component: () => import('./contact/PersonChooseDialog.vue') },
    { path: '/PersonEditor', component: () => import('./contact/PersonEditor.vue') },
    { path: '/Project', component: () => import('@skeljs/core/src/ui/demo/Project.vue') },
    { path: '/GroupAdmin', component: () => import('../src/net/bodz/lily/schema/account/GroupAdmin.vue') },
    { path: '/GroupTypeAdmin', component: () => import('../src/net/bodz/lily/schema/account/GroupTypeAdmin.vue') },
    { path: '/PolicyAdmin', component: () => import('../src/net/bodz/lily/schema/account/PolicyAdmin.vue') },
    { path: '/UserAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserAdmin.vue') },
    { path: '/UserOtherIdAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserOtherIdAdmin.vue') },
    { path: '/UserOtherIdTypeAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserOtherIdTypeAdmin.vue') },
    { path: '/UserRunAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserRunAdmin.vue') },
    { path: '/UserSecretAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserSecretAdmin.vue') },
    { path: '/UserTypeAdmin', component: () => import('../src/net/bodz/lily/schema/account/UserTypeAdmin.vue') },
    { path: '/OrgUnitAdmin', component: () => import('../src/net/bodz/lily/schema/contact/OrgUnitAdmin.vue') },
    { path: '/OrganizationAdmin', component: () => import('../src/net/bodz/lily/schema/contact/OrganizationAdmin.vue') },
    { path: '/PartyCategoryAdmin', component: () => import('../src/net/bodz/lily/schema/contact/PartyCategoryAdmin.vue') },
    { path: '/PersonAdmin', component: () => import('../src/net/bodz/lily/schema/contact/PersonAdmin.vue') },
    { path: '/PersonRoleAdmin', component: () => import('../src/net/bodz/lily/schema/contact/PersonRoleAdmin.vue') },
    { path: '/PersonTagAdmin', component: () => import('../src/net/bodz/lily/schema/contact/PersonTagAdmin.vue') },
    { path: '/PersonTagTypeAdmin', component: () => import('../src/net/bodz/lily/schema/contact/PersonTagTypeAdmin.vue') },
    { path: '/ZoneAdmin', component: () => import('../src/net/bodz/lily/schema/geo/ZoneAdmin.vue') },
    { path: '/ZoneCategoryAdmin', component: () => import('../src/net/bodz/lily/schema/geo/ZoneCategoryAdmin.vue') },
    { path: '/ExternalSiteAdmin', component: () => import('../src/net/bodz/lily/schema/inet/ExternalSiteAdmin.vue') },
    { path: '/StorageAdmin', component: () => import('../src/net/bodz/lily/schema/io/StorageAdmin.vue') },
    { path: '/CategoryDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/CategoryDefAdmin.vue') },
    { path: '/FormDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/FormDefAdmin.vue') },
    { path: '/FormParameterAdmin', component: () => import('../src/net/bodz/lily/schema/meta/FormParameterAdmin.vue') },
    { path: '/ParameterDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/ParameterDefAdmin.vue') },
    { path: '/ParameterValueAdmin', component: () => import('../src/net/bodz/lily/schema/meta/ParameterValueAdmin.vue') },
    { path: '/PhaseDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/PhaseDefAdmin.vue') },
    { path: '/PriorityDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/PriorityDefAdmin.vue') },
    { path: '/SchemaDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/SchemaDefAdmin.vue') },
    { path: '/TagDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/TagDefAdmin.vue') },
    { path: '/TagGroupDefAdmin', component: () => import('../src/net/bodz/lily/schema/meta/TagGroupDefAdmin.vue') },
    { path: '/ArticleAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleAdmin.vue') },
    { path: '/ArticleBackrefAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleBackrefAdmin.vue') },
    { path: '/ArticleCategoryAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleCategoryAdmin.vue') },
    { path: '/ArticleFavAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleFavAdmin.vue') },
    { path: '/ArticleParameterAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleParameterAdmin.vue') },
    { path: '/ArticleParameterTypeAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleParameterTypeAdmin.vue') },
    { path: '/ArticleTagAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleTagAdmin.vue') },
    { path: '/ArticleTagTypeAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleTagTypeAdmin.vue') },
    { path: '/ArticleTalkAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleTalkAdmin.vue') },
    { path: '/ArticleTalkVoteAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleTalkVoteAdmin.vue') },
    { path: '/ArticleVoteAdmin', component: () => import('../src/net/bodz/lily/schema/pub/ArticleVoteAdmin.vue') },
    { path: '/PostAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostAdmin.vue') },
    { path: '/PostBackrefAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostBackrefAdmin.vue') },
    { path: '/PostCategoryAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostCategoryAdmin.vue') },
    { path: '/PostFavAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostFavAdmin.vue') },
    { path: '/PostParameterAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostParameterAdmin.vue') },
    { path: '/PostParameterTypeAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostParameterTypeAdmin.vue') },
    { path: '/PostTagAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostTagAdmin.vue') },
    { path: '/PostTagTypeAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostTagTypeAdmin.vue') },
    { path: '/PostTalkAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostTalkAdmin.vue') },
    { path: '/PostTalkVoteAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostTalkVoteAdmin.vue') },
    { path: '/PostVoteAdmin', component: () => import('../src/net/bodz/lily/schema/pub/PostVoteAdmin.vue') },
    { path: '/BadgeAdmin', component: () => import('../src/net/bodz/lily/schema/reward/BadgeAdmin.vue') },
    { path: '/UserBadgeAdmin', component: () => import('../src/net/bodz/lily/schema/reward/UserBadgeAdmin.vue') },
    { path: '/UomAdmin', component: () => import('../src/net/bodz/lily/schema/util/UomAdmin.vue') },
    { path: '/UomRowAdmin', component: () => import('../src/net/bodz/lily/schema/util/UomRowAdmin.vue') },
    { path: '/ApiTypeAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/ApiTypeAdmin.vue') },
    { path: '/VApiAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VApiAdmin.vue') },
    { path: '/VApiCreditAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VApiCreditAdmin.vue') },
    { path: '/VApiLogAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VApiLogAdmin.vue') },
    { path: '/VAppAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VAppAdmin.vue') },
    { path: '/VAppCategoryAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VAppCategoryAdmin.vue') },
    { path: '/VAppRequestAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VAppRequestAdmin.vue') },
    { path: '/VAppRequestApiAdmin', component: () => import('../src/net/bodz/lily/schema/vapp/VAppRequestApiAdmin.vue') },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
});

export default router;
