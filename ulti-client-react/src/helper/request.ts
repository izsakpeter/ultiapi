import { Game } from "../model/game";
import axios, { AxiosRequestConfig } from "axios";


export async function Request(url: string): Promise<Game> {

    let configuration: AxiosRequestConfig = {
        timeout: 30000
    };

    configuration.baseURL = "http://localhost:8888";

    const response = await axios.get<Game>(url, configuration);
    if (response != null)
        return response.data;

    return null;
}