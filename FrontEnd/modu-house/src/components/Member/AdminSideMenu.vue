<template>
  <v-app id="inspire">
    <v-card>
      <v-navigation-drawer
        app
        clipped
        class="accent-4"
        height="100vh"
        width="20%"
        v-model="drawer"
        :mini-variant.sync="mini"
      >
        <!--모두의 집 로고 및 모두의집 타이틀 들어갈 곳-->
        <v-list-item class="px-2">
          <v-list-item-avatar>
            <v-icon>settings</v-icon>
          </v-list-item-avatar>

          <v-list-item-title> 관리 </v-list-item-title>
          <v-btn icon @click.stop="mini = !mini">
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
        </v-list-item>

        <v-divider></v-divider>
        <!-- 홈버튼부터 공지버튼까지 추가 -->
        <v-list dense>
          <!-- 메인 페이지로 가는건 필요 없는것 같아서 뺏어요 + 로그인/아웃도 -->

          <!-- other lists -->
          <v-list-item v-for="item in items" :key="item.title" :to="item.link">
            <v-list-item-icon>
              <v-icon> {{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title> {{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-card>
  </v-app>
</template>

<script>
export default {
  name: "AdminSideMenu",
  data() {
    return {
      drawer: true,
      items: [
        {
          title: "게시판 관리",
          icon: "grid_view",
          link: "/user/admin/board-management",
        },
        {
          title: "사용자 관리",
          icon: "group",
          link: "/user/admin/user-management/",
        },
        {
          title: "Q&A 관리",
          icon: "question_answer",
          link: "/user/admin/qna-management",
        },
        {
          title: "공지",
          icon: "campaign",
          link: "/user/admin/notice-management",
        },
        {
          title: "스케쥴",
          icon: "event_available",
          link: "/user/admin/schedule",
        },
        {
          title: "현황 & 통계",
          icon: "leaderboard",
          link: "/user/admin/stats",
        },
      ],
      mini: true,
      searchWord: "",
    };
  },
};
</script>

<style></style>
