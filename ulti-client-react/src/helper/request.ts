import axios, { AxiosRequestConfig } from "axios";
import { getRequestJson, RequestModel } from "../model/requestModel";
import { Response } from "../model/response";

const baseURL: string = "http://localhost:8888/";

let configuration: AxiosRequestConfig = {
    timeout: 600000
};

export async function PostRequest(reqObj: RequestModel): Promise<Response> {

    const response = await axios.post<Response>(baseURL + reqObj.dest, getRequestJson(reqObj), configuration);

    if (response.status === 200) {
        return response.data;
    } else
        return new Response(false, "ERROR: HTTP response code: " + response.status);
}