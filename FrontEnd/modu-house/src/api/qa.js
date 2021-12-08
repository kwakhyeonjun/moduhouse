import { apiInstance } from "./index.js";

const api = apiInstance();
//Q&A 게시판 글 작성 - /qna post

//Q&A 게시판 글 목록 - /qna get

function listArticle(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/qna/`, JSON.stringify(article)).then(success).catch(fail);
}
function writeAnswer(article, success, fail) {
  api.put(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

export { listArticle, writeArticle, writeAnswer };
