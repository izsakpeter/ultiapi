import { Game } from "../model/game";
import axios, { AxiosRequestConfig } from "axios";

const baseURL = "http://localhost:8888";

let configuration: AxiosRequestConfig = {
    timeout: 60000
};


export async function Request(url: string): Promise<Game> {

    configuration.baseURL = baseURL;

    const response = await axios.get<Game>(url, configuration);
    if (response != null)
        return response.data;

    return null;
}

export async function StartPostRequest(id: number): Promise<string> {
    const response = await axios.post<string>("http://localhost:8888/start", {
        id: id
    }, configuration);

    if (response.status === 200) {
        return response.data;
    } else
        return "ERROR: HTTP response code: " + response.status
}

export async function StatusPostRequest(id: number, lastTimeStamp: number): Promise<Game> {
    const response = await axios.post<Game>("http://localhost:8888/status", {
        id: id,
        lastTimeStamp: lastTimeStamp
    }, configuration);

    if (response.status === 200) {
        return response.data;
    } else {
        console.log("ERROR: HTTP response code: " + response.status);
        return null;
    }
}