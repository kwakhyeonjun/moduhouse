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
              <v-col cols="12" sm="6" md="12">
                <v-card-title class="justify-center"
                  ><h3>Sign Up</h3></v-card-title
                >
                <v-card-text align="center" justify="center">
                  <v-text-field
                    ref="username"
                    id="username"
                    v-model="user.username"
                    label="이름"
                    placeholder="이름을 입력해주세요"
                    required
                  ></v-text-field>
                  <v-text-field
                    class="mt-n5 mx-auto"
                    id="userid"
                    ref="userid"
                    v-model="user.userid"
                    label="아이디"
                    placeholder="아이디를 입력해주세요"
                    autocomplete="off"
                    required
                  ></v-text-field>

                  <v-list-item-text v-html="idComment"></v-list-item-text>
                  <v-text-field
                    id="email"
                    class="mt-n5 mx-auto"
                    ref="email"
                    v-model="user.email"
                    label="이메일"
                    placeholder="이메일을 입력해주세요"
                    required
                    @blur="idCheck()"
                  ></v-text-field>

                  <v-text-field
                    id="userpwd"
                    ref="userpwd"
                    class="mt-n5 mx-auto"
                    v-model="user.userpwd"
                    label="비밀번호"
                    type="password"
                    autocomplete="off"
                    required
                    @blur="passwordCheck()"
                    placeholder="비밀번호를 입력해주세요"
                  ></v-text-field>

                  <v-text-field
                    v-model="pwdCheck"
                    autocomplete="off"
                    label="비밀번호 확인"
                    class="mt-n5 mx-auto"
                    type="password"
                    @keyup="passwordCheck()"
                    required
                    placeholder="비밀번호를 한번 더 입력해주세요"
                  ></v-text-field>

                  <v-list-item-text class="mt-n6 mx-auto">{{
                    pwdComment
                  }}</v-list-item-text>
                </v-card-text>
                <v-divider class="mt-1"></v-divider>
                <v-card-actions>
                  <v-btn text @click="$router.go(-1)"> 취소 </v-btn>
                  <v-spacer></v-spacer>
                  <v-slide-x-reverse-transition>
                    <!-- <v-tooltip v-if="formHasErrors" left>
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn
                          icon
                          class="my-0"
                          v-bind="attrs"
                          @click="resetForm"
                          v-on="on"
                        >
                          <v-icon>mdi-refresh</v-icon>
                        </v-btn>
                      </template>
                      <span>Refresh form</span>
                    </v-tooltip> -->
                  </v-slide-x-reverse-transition>
                  <v-btn color="primary" text @click="onSubmit"> 등록 </v-btn>
                </v-card-actions>
              </v-col>
            </v-form>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </v-layout>
</template>
<script>
import { Join } from "@/api/member";
//api member에 join 추가해야겟음

export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
        email: "",
        username: "",
      },
      pwdCheck: "", //비밀번호 일치체크
      pwdComment: "", //비밀번호 불일치 시 에러메시지
      idComment: "", //아이디 중복 체크 메시지
      idUsed: false, //이미 사용중인 아이디면 true
      passwordMatch: false, //비밀번호 일치 시 true
      sidos: [
        "강원도",
        "경기도",
        "경상남도",
        "경상북도",
        "광주광역시",
        "대구광역시",
        "대전광역시",
        "부산광역시",
        "서울특별시",
        "세종특별자치시",
        "울산광역시",
        "인천광역시",
        "전라남도",
        "전라북도",
        "제주특별자치도",
        "충청남도",
        "충청북도",
      ],
    };
  } /*
  props: {
    type: { type: String },
  },*/,
  methods: {
    onSubmit() {
      let err = true;
      let msg = "";
      !this.user.userid && ((msg = "아이디를 확인해주세요"), (err = false));
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 확인해주세요"), (err = false));
      err &&
        !this.user.email &&
        ((msg = "이메일을 확인해주세요"), (err = false));
      err &&
        !this.user.username &&
        ((msg = "이름을 확인해주세요"), (err = false));

      if (!err) alert(msg);
      else {
        Join(this.user);
        this.$router.replace("/");
      }
    },
    idCheck() {
      //아이디 중복체크
      //나중에 시간되면 비동기로 얻어오자..
    },
    passwordCheck() {
      if (
        this.user.userpwd == this.pwdCheck &&
        this.user.userpwd != "" &&
        this.pwdCheck != ""
      ) {
        //same and not null
        this.pwdComment = "비밀번호가 일치합니다";
        this.passwordMatch = true;
      } else if (this.user.userpwd != "" && this.pwdCheck != "") {
        this.pwdComment = "비밀번호가 일치하지 않습니다";
        this.passwordMatch = false;
      } else if (this.user.userpwd == "" || this.pwdCheck == "") {
        this.pwdComment = "";
        this.passwordMatch = false;
      }
    },
  },
};
</script>

<style></style>
