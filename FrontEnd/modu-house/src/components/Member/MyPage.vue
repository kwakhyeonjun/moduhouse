<template>
  <v-app>
    <v-list>
      <!-- 제목 -->
      <v-list-item-title><h2>Profile</h2></v-list-item-title>
      <v-divider></v-divider>
      <v-row justify="space-around">
        <!-- gives gap -->
        <v-col md="8" />
        <v-container>
          <v-card class="mx-auto info_card" max-width="60%" height="400">
            <!-- 들어갈 내용: 반반씩 나누고 한쪽엔 사진 한쪽엔 정보쓰고싶음 -->
            <v-container>
              <v-row>
                <v-col>
                  <!-- 회원 정보-->
                  <!-- 반반영ㅇ역 2 
                  <v-col md="6">
                  </v-col>
                  -->
                  <v-list-item> </v-list-item>
                  <!-- 프로필 사진 -->
                  <v-flex md="8"> </v-flex>
                  <v-container>
                    <v-card outlined>
                      <v-card-title class="justify-center">
                        <v-icon size="150">account_circle</v-icon>
                      </v-card-title>
                      <v-card-title class="justify-center">
                        <h4 class="hello">{{ userInfo.username }} 님</h4>
                      </v-card-title>
                    </v-card>
                    <v-file-input
                      :rules="rules"
                      accept="image/png, image/jpeg, image/bmp"
                      placeholder="Pick an avatar"
                      prepend-icon="mdi-camera"
                      label="프로필 사진 선택"
                    ></v-file-input>
                    <!-- 프로필사진 선택은 후에..
                  -->
                  </v-container>
                </v-col>
                <!-- list 뿌리기 : for loop 돌리기-->

                <v-card width="50%" height="400" elevation="0">
                  <v-col></v-col>
                  <v-card-title class="justify-center">
                    <h4>회원 정보</h4>
                  </v-card-title>
                  <v-container></v-container>

                  <v-container>
                    <v-list-item>
                      <v-text-field
                        v-model="userInfo.username"
                        label="이름"
                        id="userid"
                        outlined
                        disabled
                      ></v-text-field>
                    </v-list-item>
                    <v-list-item>
                      <v-text-field
                        v-model="userInfo.email"
                        label="이메일"
                        id="email"
                        disabled
                        outlined
                        readonly
                      ></v-text-field>
                    </v-list-item>
                    <v-list-item>
                      <v-text-field
                        id="userpwd"
                        v-model="userInfo.userpwd"
                        :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                        :type="show1 ? 'text' : 'password'"
                        label="비밀번호 수정"
                        outlined
                        @click:append="show1 = !show1"
                        :readonly="isReadOnly"
                      ></v-text-field>
                    </v-list-item>

                    <v-list-item>
                      <v-spacer></v-spacer>
                      <v-btn @click="modify" :disabled="isDisabled">
                        변경 완료
                      </v-btn>
                    </v-list-item>
                  </v-container>
                </v-card>
              </v-row>
            </v-container>
          </v-card>
        </v-container>
        <v-container>
          <v-layout justify-center>
            <v-btn @click="triggered">회원 정보 수정</v-btn>
            <v-btn @click="optOut">회원 탈퇴</v-btn>
          </v-layout>

          <v-layout column justify-center align-center>
            <v-flex xs12 sm8 md6>
              <v-card-subtitle
                >회원 정보 수정 버튼을 클릭하여 비밀번호를 변경하실 수
                있습니다.</v-card-subtitle
              >
            </v-flex>
          </v-layout>
        </v-container>

        <!-- 회원가입 버튼 -->
      </v-row>
    </v-list>
  </v-app>
</template>

<script>
//import SignIn from "@/components/Member/SignIn.vue";

import { mapState, mapMutations } from "vuex";
import { deleteUser, updatePassword } from "@/api/member";

const memberStore = "memberStore";
export default {
  name: "MyPage",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      show1: false,
      isReadOnly: true,
      isDisabled: true,
    };
  },

  methods: {
    modify() {
      console.log("hk");
      console.log(this.userInfo.userpwd);
      let err = true;
      let msg = "";
      !this.userInfo.userpwd &&
        ((msg = "비밀번호를 확인해주세요"), (err = false));
      if (!err) alert(msg);
      else {
        updatePassword(this.userInfo);
        alert("비밀번호가 정상적으로 수정되었습니다.");
        this.isDisabled = !this.isDisabled;
        this.isReadOnly = !this.isReadOnly;
      }
    },
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    optOut() {
      deleteUser(this.userInfo.userid);
      console.log(this.userInfo.userid);
      alert("회원 탈퇴 완료");
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.$router.push({ name: "Main" });
    },
    triggered() {
      this.isReadOnly = !this.isReadOnly;
      this.isDisabled = !this.isDisabled;
    },
  },
};
</script>

<style></style>
