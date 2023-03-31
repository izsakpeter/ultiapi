import { Game } from "../model/game";
import axios, { AxiosRequestConfig } from "axios";
import { getRequestJson, RequestModel } from "../model/requestModel";
import { Response } from "../model/response";

const baseURL: string = "http://localhost:8888/";

let configuration: AxiosRequestConfig = {
    timeout: 600000
};

export async function StatusPostRequest(id: number, lastTimeStamp: number): Promise<Game> {
    const response = await axios.post<Game>(baseURL + "status", {
        id: id,
        lastTimeStamp: lastTimeStamp
    }, configuration);

    if (response.status !== 200) {
        console.log("ERROR: HTTP response code: " + response.status);
    }

    return response.data;
}

export async function PostRequest(reqObj: RequestModel): Promise<Response> {

    const response = await axios.post<Response>(baseURL + reqObj.dest, getRequestJson(reqObj), configuration);

    if (response.status === 200) {
        return response.data;
    } else
        return new Response(false, "ERROR: HTTP response code: " + response.status);
}