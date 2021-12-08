<template>
  <div id="app">
    <v-app id="inspire">
      <v-card>
        <v-navigation-drawer
          clipped
          class="accent-4"
          height="100vh"
          v-model="drawer"
          :mini-variant.sync="mini"
          permanent
        >
          <!--모두의 집 로고 및 모두의집 타이틀 들어갈 곳-->
          <v-list-item-group color="#a8e0ff">
            <v-list-item class="px-2">
              <v-list-item-avatar>
                <v-icon>menu</v-icon>
              </v-list-item-avatar>

              <v-list-item-title>모두의집</v-list-item-title>

              <v-btn icon @click.stop="mini = !mini">
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>
            </v-list-item>

            <v-divider></v-divider>
            <!-- 홈버튼부터 공지버튼까지 추가 -->
            <v-list dense>
              <v-list-item>
                <!-- to main page -->
                <v-list-item-icon>
                  <v-icon>home </v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>
                    <a @click="$router.push(`/`)" style="color: inherit">
                      메인으로
                    </a></v-list-item-title
                  >
                </v-list-item-content>
              </v-list-item>

              <!-- to name search -->
              <v-list-item to="/map/name">
                <v-list-item-icon>
                  <v-icon>domain </v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title> 아파트이름으로 찾기 </v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <!-- to dong search -->
              <v-list-item to="/map/dong">
                <v-list-item-icon>
                  <v-icon>real_estate_agent </v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title> 지역으로 찾기 </v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <!-- other lists -->
              <v-list-item v-for="item in items" :key="item.title">
                <v-list-item-icon>
                  <v-icon> {{ item.icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>
                    <a
                      @click="$router.push(`${item.link}`)"
                      style="color: inherit"
                    >
                      {{ item.title }}
                    </a></v-list-item-title
                  >
                </v-list-item-content>
              </v-list-item>
              <!-- 로그인 한 상태라면 -->
              <v-list-item v-if="userInfo">
                <v-list-item-icon>
                  <v-icon>logout </v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>
                    <a @click.prevent="onClickLogout" style="color: inherit">
                      로그아웃
                    </a>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <!-- 로그인 안했다면 -->
              <v-list-item v-else active>
                <v-list-item-icon>
                  <v-icon>login </v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title>
                    <a @click="login" style="color: inherit"> 로그인 </a>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>

            <!-- 로그인 및 로그아웃 -->

            <v-list dense> </v-list>
          </v-list-item-group>
        </v-navigation-drawer>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";
export default {
  name: "MapNavBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data() {
    return {
      drawer: true,
      items: [
        { title: "게시판", icon: "people", link: "../board" },
        { title: "공지", icon: "campaign", link: "../notice" },
      ],
      mini: true,
      searchWord: "",
    };
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("로그아웃 되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "Map" });
    },
    login() {
      this.$router.push("/user/signin");
    },
  },
};
</script>

<style>
.v-text-field .v-input__control .v-input__slot {
  min-height: auto !important;
  /* display: flex !important; */
}
</style>
