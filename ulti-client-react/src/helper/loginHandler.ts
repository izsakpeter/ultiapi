import { User } from "../model/user";

export function getSessionId(username: string, password: string): number {
    //ez majd db lesz a jővőben

    if (username != "" && password != "") {
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

    users.push(new User(1, "IPéter", "8888"));
    users.push(new User(2, "NNorbi", "6666"));
    users.push(new User(3, "GNorbi", "0420"));
    users.push(new User(4, "SztárLaci", "0421"));
    users.push(new User(5, "Ándika", "4444"));

    return users;
}