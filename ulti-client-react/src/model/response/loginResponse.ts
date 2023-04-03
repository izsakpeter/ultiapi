export class LoginResponse {
  public isSuccess: boolean;
  public errorMsg: string;
  public permission: number;
  public playerId: number;

  public constructor(
    isSuccess: boolean,
    errorMsg: string,
    permission: number,
    playerId: number
  ) {
    this.isSuccess = isSuccess;
    this.errorMsg = errorMsg;
    this.permission = permission;
    this.playerId = playerId;
  }
}
