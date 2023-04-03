import axios from "axios";
import { getRequestJson, RequestModel } from "../model/requestModel";
import { LoginResponse } from "../model/response/loginResponse";
import { baseURL, configuration } from "./config";

export async function loginRequest(
  reqObj: RequestModel
): Promise<LoginResponse> {
  const response = await axios.post<LoginResponse>(
    baseURL + "login",
    getRequestJson(reqObj),
    configuration
  );

  if (response.status === 200) {
    return response.data;
  } else
    return new LoginResponse(
      false,
      "ERROR: HTTP response code: " + response.status,
      0,
      -1
    );
}
