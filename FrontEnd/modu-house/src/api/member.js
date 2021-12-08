import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function Join(user, success, fail) {
  api.post(`/user/signup`, JSON.stringify(user)).then(success).catch(fail);
}
function updatePassword(user, success, fail) {
  api.put("/user", JSON.stringify(user)).then(success).catch(fail);
}
function deleteUser(userid, success, fail) {
  //백에서 when triggered-> change status to inactive
  api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}
function userList(param, success, fail) {
  api
    .post(`/user/admin/user-management/`, { params: param })
    .then(success)
    .catch(fail);
}

// function logout(success, fail)

export { login, findById, Join, updatePassword, deleteUser, userList };
