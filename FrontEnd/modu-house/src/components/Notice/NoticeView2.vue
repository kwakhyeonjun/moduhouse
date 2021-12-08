<template>
  <v-app id="main">
    <nav-bar />
    <!--네비바-->
    <!-- 어차피 아무것도 없어서 지웠어여 죄송해여 흑-->
    <div>
      <v-container>
        <!----------->
        <v-list>
          <v-list-item-title><h2>📢 공지사항</h2></v-list-item-title>
        </v-list>
        <v-divider></v-divider>
        <v-row justify="space-around">
          <v-col cols="3" />
          <v-col cols="6">
            <v-container>
              <v-btn plain style="float: left" to="../notice/">목록</v-btn>
            </v-container>
          </v-col>
          <v-col cols="3" />
        </v-row>
        <!-------------->
        <v-row>
          <v-col cols="3" />
          <v-col cols="6">
            <v-container>
              <v-card class="mx-auto info_card" max-width="100%">
                <v-card-text style="text-align: center">
                  <p style="text-align: center; margin-bottom: 0px">공지사항</p>
                  <v-card-subtitle><h1>개인정보처리방침</h1> </v-card-subtitle>
                  <v-divider></v-divider>
                  <v-card-text>
                    <!-- 공지 내용 작성 -->
                    <privacy-policy></privacy-policy>
                  </v-card-text>
                </v-card-text>
              </v-card>
            </v-container>
            <v-container> </v-container>
          </v-col>
          <v-col cols="3" />
        </v-row>
        <!----------->
      </v-container>
    </div>
    <v-app>
      <footer-bar></footer-bar>
    </v-app>
  </v-app>
</template>
<script>
//import navibar
import NavBar from "@/components/layout/NavBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import PrivacyPolicy from "@/components/Member/child/adminFcn/notice/PrivacyPolicy.vue";

export default {
  name: "Main",
  components: {
    NavBar,
    FooterBar,
    PrivacyPolicy,
  },
};
</script>

PrivacyPolicy
<style>
.main-container {
  margin: 2%;
}
.info_card {
  border-bottom: 1px solid #d4d4d4;
  margin-bottom: 10px;
}
.main-right-container {
  margin: 25% 10%;
}
.v-application--wrap {
  max-width: 100% !important;
  min-height: 100vh !important;
}
#loca {
  text-align: center;
  padding: 0;
}
</style>
