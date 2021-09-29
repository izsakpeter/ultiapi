export class User {
    public id: number;
    public username: string;
    public password: string;

    public constructor(id: number, username: string, password: string) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}