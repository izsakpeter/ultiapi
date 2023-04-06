import axios from "axios";
import { RequestModel, getRequestJson } from "../model/requestModel";
import { baseURL, configuration } from "./config";
import { Response } from "../model/response";

export async function postRequest(reqObj: RequestModel): Promise<Response> {
  const response = await axios.post<Response>(
    baseURL + reqObj.dest,
    getRequestJson(reqObj),
    configuration
  );

  if (response.status === 200) {
    return response.data;
  } else
    return new Response(false, "ERROR: HTTP response code: " + response.status);
}
