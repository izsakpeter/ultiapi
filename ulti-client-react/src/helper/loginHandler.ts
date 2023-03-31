import { User } from "../model/user";

export function getSessionId(username: string, password: string): number {
    //ez majd db lesz a jővőben

    if (username !== "" && password !== "") {
        let allUser = getAlluser();

        for (let i = 0; i < allUser.length; i++) {
            if (allUser[i].username === username && allUser[i].password === password)
                return allUser[i].id;
        }
    }

    return -1;
}

export function getUsernameById(id: number): string {
    let allUser = getAlluser();

    for (let i = 0; i < allUser.length; i++) {
        if (allUser[i].id === id)
            return allUser[i].username
    }

    return "ismeretlen";
}

function getAlluser(): Array<User> {
    let users = [];

    users.push(new User(1, "IP", "8"));
    users.push(new User(2, "NN", "6"));
    users.push(new User(3, "GN", "0"));
    users.push(new User(4, "SzL", "1"));
    users.push(new User(5, "Á", "4"));

    return users;
}