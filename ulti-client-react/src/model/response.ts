export class Response {
    public isSuccess: boolean;
    public message: string;

    public constructor(isSuccess: boolean, message: string) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}