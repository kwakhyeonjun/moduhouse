<template>
  <!-- <v-app id="nav-hor"> -->
  <v-toolbar flat outlined>
    <!--Home-->
    <v-container>
      <v-layout row fill-height>
        <v-flex xs4 md2></v-flex>
        <v-flex xs12 md6>
          <v-btn x-large plain :to="{ name: 'Main' }"
            ><img src="@/assets/logo.png" width="120px"
          /></v-btn>
          <v-btn x-large plain :to="{ name: 'Community' }"> 커뮤니티 </v-btn>
          <!-- <v-btn x-large plain :to="{ name: 'MainSubscription' }"> 분양 </v-btn> -->
          <v-btn x-large plain :to="{ name: 'MapSearchName' }"> Map </v-btn>
          <v-btn x-large plain :to="{ name: 'Notice' }"> 공지 </v-btn>
        </v-flex>

        <v-flex xs4 md4>
          <v-btn
            icon
            id="mode-switcher"
            @click="$vuetify.theme.dark = !$vuetify.theme.dark"
          >
            <v-icon :color="$vuetify.theme.dark ? '#a8e0ff' : '#a8e0ff'">
              {{ $vuetify.theme.dark ? "light_mode" : "dark_mode" }}
            </v-icon>
          </v-btn>
          <!-- 일단 ui 끝나면, 로그인 이미 한 경우는 이거 안보이게 수정할 것 
            <v-btn :to="SignIn" outlined>로그인</v-btn>
            <div v-if="isClicked">
            </div>
            -->
          <v-btn v-if="!userInfo" :to="{ name: 'SignIn' }" outlined
            >로그인</v-btn
          >
          <v-btn
            x-large
            plain
            :to="{ name: 'MyPage' }"
            v-if="userInfo && userInfo.userid != 'admin'"
          >
            마이페이지
          </v-btn>

          <v-btn
            x-large
            plain
            :to="{ name: 'AdminPage' }"
            v-if="userInfo && userInfo.userid == 'admin'"
          >
            관리자페이지
          </v-btn>

          <v-btn v-if="userInfo" @click.prevent="onClickLogout" outlined
            >로그아웃</v-btn
          >

          <!-- -->
        </v-flex>
      </v-layout>
    </v-container>
  </v-toolbar>
  <!-- </v-app> -->
</template>

<script>
//import SignIn from "@/components/Member/SignIn.vue";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NavBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("로그아웃 되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "Main" });
    },
  },
};
</script>

<style></style>
