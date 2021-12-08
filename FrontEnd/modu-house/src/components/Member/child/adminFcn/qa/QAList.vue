<template>
  <v-container>
    <v-data-table
      v-model="selected"
      @input="enterSelect()"
      @click:row="clicked"
      :headers="headers"
      :items="qna"
      item-key="articleno"
      class="elevation-1"
      :search="search"
    >
      <!-- 나중에 시간 나면 :single-select="single-select"로 바꾸고 multiple delete로..-->
      <template v-slot:top>
        <v-text-field
          v-model="search"
          label="문의 제목, 아이디, 이메일, 카테고리 검색"
          class="mx-8"
        ></v-text-field>
      </template>
      <!-- 이메일 눌러서 이메일 보내기-->
      <template #item.email="{ value }">
        <a :href="`mailto:${value}`"> {{ value }} </a>
      </template>

      <template #item.content="{ value }">
        <v-expansion-panels accordion>
          <v-expansion-panel elevation-0>
            <v-expansion-panel-header> 상세 보기</v-expansion-panel-header>
            <v-expansion-panel-content>
              {{ value }}
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </template>

      <template v-slot:item.status="{ item }">
        <!--
        <v-btn
          v-if="item.status == '답변'"
          color="blue darken-3"
          class="white--text"
          v-model="stat"
          @click="answer"
          >답변</v-btn
        >
        <v-btn v-else disabled>완료</v-btn>
        -->
        <v-btn
          v-if="item.status == '답변'"
          id="save"
          type="submit"
          v-bind:class="{ disabled: clicked, primary: !clicked }"
          class="btn my-2"
          v-on:click="added"
        >
          {{ clicked ? "완료" : "답변" }}
        </v-btn>
      </template>
    </v-data-table>
    <v-layout column justify-center align-center>
      <v-flex xs12 sm8 md6>
        <v-card-subtitle
          >이메일로 답변 전송 후, 답변 버튼을 눌러 완료로
          표시해주세요.</v-card-subtitle
        >
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { listArticle } from "@/api/qa";
//import QADetail from "@/components/Member/child/adminFcn/qa/QADetail.vue";
//write article
export default {
  name: "QA",
  components: {},
  methods: {
    answer() {
      // 지움
    },
    added() {
      this.clicked = true;
    },
  },

  props: {},
  data() {
    return {
      //질의응답
      dialog: false,
      stat: "답변",
      clicked: false,
      search: "",
      page: "",
      show: false,
      headers: [
        {
          text: "no",
          align: "start",
          sortable: true,
          value: "articleno",
        },
        { text: "아이디", value: "userid" },
        { text: "제목", value: "subject" },
        { text: "이메일", value: "email" },
        { text: "문의날짜", value: "regtime" },
        { text: "카테고리", value: "category" },
        { text: "내용", value: "content" },
        { text: "답변여부", value: "status" },
      ],

      qna: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.qna = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
