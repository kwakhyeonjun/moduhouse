import { apiInstance } from "./index.js";

const api = apiInstance();

function listInterest(success, fail) {
  api.get(`/map/interest`).then(success).catch(fail);
}

export { listInterest };
