<template>
  <v-container height="100vh">
    <v-card height="100vh" elevation="0">
      <v-card
        :elevation="hover ? 12 : 2"
        class="mx-auto"
        height="600"
        max-width="600"
      >
        <v-card-text align="center">
          <v-form ref="form">
            <v-container>
              <v-text-field
                ref="userid"
                id="userid"
                label="작성자"
                label-for="userid"
                v-model="userInfo.userid"
                style="width: 80%"
                disabled
                outlined
              ></v-text-field>
              <v-text-field
                ref="email"
                id="email"
                label="이메일"
                label-for="email"
                v-model="userInfo.email"
                style="width: 80%"
                disabled
                outlined
              ></v-text-field>
              <!-- 카테고리 설정 -->
              <v-select
                :items="items"
                item-text="cat"
                label="질문 카테고리"
                value="회원가입"
                id="category"
                v-model="qna.category"
                solo
                outlined
                style="width: 80%"
              ></v-select>
              <v-text-field
                ref="subject"
                id="subject"
                label="문의 제목"
                solo
                outlined
                v-model="qna.subject"
                style="width: 80%"
                value=""
              ></v-text-field>

              <v-textarea
                style="width: 80%"
                ref="content"
                id="content"
                label="문의 내용을 작성해 주세요"
                outlined
                v-model="qna.content"
                value=""
                solo
                name="input-7-4"
              ></v-textarea>
              <v-row>
                <v-list-item>
                  <v-col md="13">
                    <v-btn
                      elevation="2"
                      large
                      outlined
                      plain
                      type="button"
                      @click="onSubmit"
                      class="mx-5"
                      >문의</v-btn
                    >

                    <v-btn
                      elevation="2"
                      large
                      outlined
                      plain
                      @click="$router.go(-1)"
                      >취소</v-btn
                    >
                  </v-col>
                </v-list-item>
              </v-row>
            </v-container>
          </v-form>
        </v-card-text>
      </v-card>
    </v-card>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import { writeArticle } from "@/api/qa";

const memberStore = "memberStore";
export default {
  name: "AskForm",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      items: ["로그인", "회원가입", "커뮤니티", "분양", "지도", "매매", "그외"],
      qna: {
        userid: "",
        email: "",
        category: "",
        subject: "",
        content: "",
        articleno: 0,
      },
    };
  },
  props: {
    type: { type: String },
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      // alert("문의");
      let err = true;
      let msg = "";
      !this.qna.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err; /*&&
        this.qna.category == "질문 카테고리" &&
        ((msg = "카테고리를선택해주세요"),
        (err = false),
        this.$refs.content.focus());*/
      if (!err) alert(msg);
      else {
        //alert("noerr");
        writeArticle(
          {
            userid: this.userInfo.userid,
            email: this.userInfo.email,
            category: this.qna.category,
            subject: this.qna.subject,
            content: this.qna.content,
          },
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.$router.go(-1);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
  //로그인 안되어있으면 kick out
  mounted() {
    if (!this.userInfo) {
      alert("로그인이 필요한 기능입니다");
      this.$router.push("/user/signin");
    }
  },
};
</script>

<style></style>
