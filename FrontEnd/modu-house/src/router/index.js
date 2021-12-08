import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";
import Map from "@/views/Map.vue";
import Community from "@/views/Community.vue";
import Sale from "@/views/Sale.vue";

/*회원*/
import Member from "@/views/Member.vue";
import MemberLogin from "@/components/Member/MemberLogin.vue";
import MemberJoin from "@/components/Member/MemberJoin.vue";
import MyPage from "@/components/Member/MyPage.vue";

import MyInfoDetail from "@/components/Member/child/MyInfoDetail.vue";

/* 관리자 */
import AdminPage from "@/components/Member/AdminPage.vue";
/*회원 정보 관리 탭*/
import UserManagement from "@/components/Member/child/UserManagement.vue";
import MemberList from "@/components/Member/child/adminFcn/user/MemberList.vue";
/*게시글 관리 탭*/
import BoardManagement from "@/components/Member/child/BoardManagement.vue";
import BoardList from "@/components/Member/child/adminFcn/board/BoardList.vue";
/* QA 관리 탭 */
import QnaManagement from "@/components/Member/child/QnaManagement.vue";
import QAList from "@/components/Member/child/adminFcn/qa/QAList.vue";
/*공지 관리 탭 */
import NoticeManagement from "@/components/Member/child/NoticeManagement.vue";
import Notices from "@/components/Member/child/adminFcn/notice/Notices.vue";
import NoticeCreate from "@/components/Member/child/adminFcn/notice/NoticeCreate.vue";

import Statistics from "@/components/Member/child/Statistics.vue";
import Schedule from "@/components/Member/child/ScheduleManagement.vue";
import MainPage from "@/components/MainPage/MainPage.vue";

/* 게시판 탭 */
import MainBoard from "@/components/Board/MainBoard.vue";
/* 자유게시판 */
//import FreeBoard from "@/components/Board/FreeBoard/FreeBoard.vue";
import FreeBoard from "@/components/Board/FreeBoard/NewFreeBoard.vue";
import FreeBoardView from "@/components/Board/FreeBoard/FreeBoardView.vue";
import FreeBoardUpdate from "@/components/Board/FreeBoard/FreeBoardUpdate.vue";
import FreeBoardCreate from "@/components/Board/FreeBoard/FreeBoardCreate.vue";
/* 정보게시판 */
//import DataBoard from "@/components/Board/DataBoard/DataBoard.vue";
import DataBoard from "@/components/Board/DataBoard/NewDataBoard.vue";
import DataBoardView from "@/components/Board/DataBoard/DataBoardView.vue";
import DataBoardUpdate from "@/components/Board/DataBoard/DataBoardUpdate.vue";
import DataBoardCreate from "@/components/Board/DataBoard/DataBoardCreate.vue";
/* 지역게시판 */
//import LocalBoard from "@/components/Board/LocalBoard/LocalBoard.vue";
import LocalBoard from "@/components/Board/LocalBoard/NewLocalBoard.vue";
import LocalBoardView from "@/components/Board/LocalBoard/LocalBoardView.vue";
import LocalBoardUpdate from "@/components/Board/LocalBoard/LocalBoardUpdate.vue";
import LocalBoardCreate from "@/components/Board/LocalBoard/LocalBoardCreate.vue";

/* 공유게시판 */
//import ShareBoard from "@/components/Board/ShareBoard/ShareBoard.vue";
import ShareBoard from "@/components/Board/ShareBoard/NewShareBoard.vue";
import ShareBoardView from "@/components/Board/ShareBoard/ShareBoardView.vue";
import ShareBoardUpdate from "@/components/Board/ShareBoard/ShareBoardUpdate.vue";
import ShareBoardCreate from "@/components/Board/ShareBoard/ShareBoardCreate.vue";

/*Q&A 페이지 */
import QA from "@/views/QA.vue";
import AskForm from "@/components/Board/Q&A/child/AskForm.vue";
import QABoard from "@/components/Board/Q&A/QABoard.vue";

/* 지도 */
import MapSearchDong from "@/components/Map/MapSearchDong.vue";
import MapSearchName from "@/components/Map/MapSearchName.vue";
import MapDetail from "@/components/Map/MapDetail.vue";

import MainSubscription from "@/components/subscription/MainSubscription.vue";
/* 공지 사항 */
import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/Notice/NoticeList.vue";
import NoticeView1 from "@/components/Notice/NoticeView1.vue";
import NoticeView2 from "@/components/Notice/NoticeView2.vue"; //떄려박은 페이지
import store from "@/store/index.js";
Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
    children: [
      //커뮤니티, 분양, 공지,로그인 on navbar-> 분양만 따로 네이버 지도처럼 만들기
      {
        path: "/",
        name: "Main",
        component: MainPage,
      },
      //테스트중
      {
        path: "/sales",
        name: "Sale",
        component: Sale,
      },

      {
        path: "/user",
        name: "Member",
        component: Member,
        children: [
          {
            path: "signin",
            name: "SignIn",
            component: MemberLogin,
          },
          {
            path: "signup",
            name: "SignUp",
            component: MemberJoin,
          },
          {
            path: "mypage",
            name: "MyPage",
            beforeEnter: onlyAuthUser,
            component: MyPage,
            children: [
              {
                path: "detail",
                name: "Detail",
                component: MyInfoDetail,
              },
            ],
          },
          {
            path: "admin",
            name: "AdminPage",
            component: AdminPage,
            beforeEnter: onlyAuthUser,
            redirect: "/user/admin/stats",
            children: [
              {
                path: "user-management",
                name: "UserManagement",
                redirect: "/user/admin/user-management/",
                component: UserManagement,
                children: [
                  {
                    path: "/",
                    name: "members",
                    component: MemberList,
                  },
                ],
              },
              {
                path: "board-management",
                name: "BoardManagement",
                component: BoardManagement,
                children: [
                  {
                    path: "/",
                    name: "boards",
                    component: BoardList,
                  },
                ],
              },
              {
                path: "qna-management",
                name: "QnaManagement",
                component: QnaManagement,
                children: [
                  {
                    path: "/",
                    name: "qas",
                    component: QAList,
                  },
                ],
              },

              {
                path: "notice-management",
                name: "NoticeManagement",
                component: NoticeManagement,
                children: [
                  {
                    path: "/",
                    name: "Notices",
                    component: Notices,
                  },

                  {
                    path: "/create",
                    name: "NoticeCreate",
                    component: NoticeCreate,
                  },
                ],
              },
              {
                path: "stats",
                name: "Statistics",
                component: Statistics,
              },
              {
                path: "schedule",
                name: "Schedule",
                component: Schedule,
              },
            ],
          },
        ],
      },
    ],
  },
  {
    path: "/board",
    name: "Community",
    component: Community,
    redirect: "/board/",
    children: [
      {
        path: "/",
        name: "MainBoard",
        component: MainBoard,
      },
      // FreeBoard
      {
        path: "free",
        name: "FreeBoard",
        component: FreeBoard,
        /*    children: [
          {
            path: "/",
            name: "FreeBoardList",
            component: FreeBoardList,
          },
        ],*/
      },
      {
        path: "free/detail/:articleno",
        name: "FreeBoardView",
        beforeEnter: onlyAuthUser,
        component: FreeBoardView,
      },
      {
        path: "free/update/:articleno",
        name: "FreeBoardUpdate",
        beforeEnter: onlyAuthUser,
        component: FreeBoardUpdate,
      },
      {
        path: "free/create",
        name: "FreeBoardCreate",
        beforeEnter: onlyAuthUser,
        component: FreeBoardCreate,
      },
      // DataBoard
      {
        path: "data",
        name: "DataBoard",
        component: DataBoard,
      },
      {
        path: "data/detail/:articleno",
        name: "DataBoardView",
        beforeEnter: onlyAuthUser,
        component: DataBoardView,
      },
      {
        path: "data/update/:articleno",
        name: "DataBoardUpdate",
        beforeEnter: onlyAuthUser,
        component: DataBoardUpdate,
      },
      {
        path: "data/create",
        name: "DataBoardCreate",
        beforeEnter: onlyAuthUser,
        component: DataBoardCreate,
      },
      // LocalBoard
      {
        path: "local",
        name: "LocalBoard",
        component: LocalBoard,
      },
      {
        path: "local/detail/:articleno",
        name: "LocalBoardView",
        beforeEnter: onlyAuthUser,
        component: LocalBoardView,
      },
      {
        path: "local/update/:articleno",
        name: "LocalBoardUpdate",
        beforeEnter: onlyAuthUser,
        component: LocalBoardUpdate,
      },
      {
        path: "local/create",
        name: "LocalBoardCreate",
        beforeEnter: onlyAuthUser,
        component: LocalBoardCreate,
      },
      // ShareBoard
      {
        path: "share",
        name: "ShareBoard",
        component: ShareBoard,
      },
      {
        path: "share/detail/:articleno",
        name: "ShareBoardView",
        beforeEnter: onlyAuthUser,
        component: ShareBoardView,
      },
      {
        path: "share/update/:articleno",
        name: "ShareBoardUpdate",
        beforeEnter: onlyAuthUser,
        component: ShareBoardUpdate,
      },
      {
        path: "share/create",
        name: "ShareBoardCreate",
        beforeEnter: onlyAuthUser,
        component: ShareBoardCreate,
      },
    ],
  },
  //Q & A 페이지
  {
    path: "/qa",
    name: "QA",
    component: QA,
    redirect: "/qa/",
    children: [
      {
        path: "/",
        name: "QABoard",
        component: QABoard,
      },
      {
        path: "ask",
        name: "AskForm",
        component: AskForm,
      },
    ],
  },
  //네비바 같이 안데려가니까 따로 만들기..
  {
    path: "/map",
    name: "Map",
    component: Map,
    children: [
      {
        path: "dong",
        name: "MapSearchDong",
        component: MapSearchDong,
      },
      {
        path: "name",
        name: "MapSearchName",
        component: MapSearchName,
      },
      {
        path: "detail",
        name: "MapDetail",
        component: MapDetail,
      },
    ],
  },
  {
    path: "/subs",
    name: "MainSubscription",
    component: MainSubscription,
    children: [],
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/",
    children: [
      {
        path: "/",
        name: "NoticeList",
        component: NoticeList,
      },
    ],
  },
  {
    path: "/notice/1",
    name: "NoticeView1",
    component: NoticeView1,
  },
  {
    path: "/notice/2",
    name: "NoticeView2",
    component: NoticeView2,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
