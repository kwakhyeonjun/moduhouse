<template>
  <v-layout col>
    <v-row align="center" justify="center">
      <v-col>
        <v-card
          elevation="hover ? 12 : 2"
          class="mx-auto"
          height="450"
          max-width="600"
          style="margin-bottom: 190px"
        >
          <v-container align="center" justify="center">
            <v-form align="center" justify="center">
              <v-alert
                :value="alert"
                type="error"
                v-if="isLoginError"
                dismissible
              >
                아이디 또는 비밀번호를 확인하세요
              </v-alert>
              <v-alert v-else> </v-alert>

              <v-col cols="12" sm="6" md="12">
                <v-card-title class="justify-center"
                  ><h3>Login</h3></v-card-title
                >
                <v-col></v-col>
                <v-row>
                  <v-col md="2"></v-col>
                  <v-text-field
                    id="userid"
                    v-model="user.userid"
                    required
                    label="id"
                    type="id"
                    placeholder="아이디 입력해주세요"
                    filled
                    rounded
                    dense
                    @keyup.enter="confirm"
                  ></v-text-field>
                  <v-col md="2"></v-col>

                  <v-col md="2"></v-col>
                  <v-text-field
                    id="userpwd"
                    v-model="user.userpwd"
                    required
                    label="password"
                    type="password"
                    placeholder="비밀번호를 입력해주세요"
                    filled
                    rounded
                    dense
                    @keyup.enter="confirm"
                  ></v-text-field>
                  <v-col md="2"></v-col>
                </v-row>
              </v-col>
              <!-- buttons-->
              <v-row>
                <v-list-item>
                  <v-col md="13">
                    <v-btn
                      elevation="2"
                      large
                      outlined
                      plain
                      type="button"
                      @click="confirm"
                      class="mx-5"
                      >Login</v-btn
                    >

                    <v-btn elevation="2" large outlined plain @click="cancel"
                      >Cancel</v-btn
                    >
                  </v-col>
                </v-list-item>
              </v-row>
              <!-- 링크 -->
              <v-row>
                <v-list-item>
                  <v-col md="12">
                    <!-- features for later ? -->
                    <v-btn
                      depressed
                      elevation="0"
                      plain
                      raised
                      text
                      tile
                      @click="find"
                      >비밀번호 찾기</v-btn
                    >
                    |
                    <!-- need to create signup page-->
                    <v-btn
                      type="button"
                      :to="{ name: 'SignUp' }"
                      elevation="0"
                      plain
                      raised
                      text
                      tile
                      >회원 가입</v-btn
                    >
                  </v-col>
                </v-list-item>
              </v-row>
            </v-form>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      alert: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]), //보드, 하우스, 맴버를 나눠놔서 memberStroe에 쓴거임-> index.js 봐보기
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        // this.$router.push({ name: "Main" });
        this.$router.go(-1);
        console.log("islogin");
      } else if (this.isLoginError) {
        this.alert = true;
      }
    },
    cancel() {
      this.alert = false;
      this.$router.go(-1);
    },
    find() {},
  },
};
</script>

<style>
.signinform {
  background-color: rgb(255, 255, 255);
  border: 1px solid;
  width: 30%;
  height: 80%;

  color: black;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.v-text-field {
}
/*
::v-deep .v-btn {
  padding: 100%;
}*/
.namej {
  position: fixed;
}
</style>
