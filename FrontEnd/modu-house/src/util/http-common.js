//localhost-> 바꾸기118.67.143.67
import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "http://localhost:9999/vue", // 로컬에서 테스트
  baseURL: "http://118.67.143.67:9999/vue", // 서버용URL
  headers: {
    "Content-type": "application/json",
  },
});
