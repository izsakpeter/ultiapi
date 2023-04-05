import { H1 } from "@blueprintjs/core";
import { useState } from "react";
import { RequestModel } from "../model/requestModel";

interface ILogin {
    loginRequest: (reqObj: RequestModel) => void,
}

export default function Login({ loginRequest }: ILogin) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event: any) => {

        if (!!username && !!password) {
            event.preventDefault();

            let reqObj: RequestModel = {
                dest: "login",
                username: username,
                password: password
            }

            loginRequest(reqObj);
        }
    }

    return (
        <div className={"login"}>
            <div className={"login-panel"}>
                <div><H1>Bejelentkezés</H1></div>
                <form onSubmit={handleSubmit}>
                    <div>Név</div>
                    <div><input type="text" value={username} onChange={(event) => setUsername(event.target.value)} /></div>
                    <div>Jelszó</div>
                    <div><input type="password" value={password} onChange={(event) => setPassword(event.target.value)} /></div>
                    <div> <input type="submit" value="Bejelenkezés" /></div>
                </form>
            </div>
        </div>
    )
}