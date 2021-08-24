import React = require("react");
import { Request } from "../helper/request";
import { getCallList } from "../model/call";
import { Game } from "../model/game";
import { PassOrJoin } from "./PassOrJoin";

export class Call extends React.Component<{ talon: Array<number>, game: Game, hand: Array<number> }, { colorId: any, callList: Array<number>, game: Game }>{

    constructor(props) {
        super(props);

        this.call = this.call.bind(this);
        this.onChooseColor = this.onChooseColor.bind(this);
        this.onChoosePassz = this.onChoosePassz.bind(this);
        this.onChoose40100 = this.onChoose40100.bind(this);
        this.onChooseUlti = this.onChooseUlti.bind(this);
        this.onChooseBetli = this.onChooseBetli.bind(this);
        this.onChooseDuri = this.onChooseDuri.bind(this);
        this.onChooseSzDuri = this.onChooseSzDuri.bind(this);
        this.onChoose20100 = this.onChoose20100.bind(this);
        this.onChooseTBetli = this.onChooseTBetli.bind(this);
        this.onChooseTDuri = this.onChooseTDuri.bind(this);
        this.onChooseTSzDuri = this.onChooseTSzDuri.bind(this);

        this.state = {
            colorId: 0,
            callList: [],
            game: null
        }
    }

    render() {

        if (this.props.game.activePlayer != this.props.game.player.id)
            return <></>;

        if (this.props.hand.length == 10 && this.props.talon.length == 0) {
            return <PassOrJoin game={this.props.game} />;
        } else if (this.props.hand.length + this.props.talon.length == 12) {
            return (
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <td onChange={this.onChooseColor}>
                                    <input type="radio" defaultChecked value="1" name="sv" /> MAKK
                                    <input type="radio" value="2" name="sv" /> ZOLD
                                    <input type="radio" value="3" name="sv" /> TOK
                                    <input type="radio" value="4" name="sv" /> PIROS
                                </td>
                            </tr>
                            <tr><td><input type="checkbox" onChange={this.onChoosePassz} /> passz </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChoose40100} /> 40-100 </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseUlti} /> ulti </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseBetli} /> betli </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseDuri} /> duri </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseSzDuri} /> szintelen duri </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChoose20100} /> 20-100 </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseTBetli} /> teritett betli </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseTDuri} /> teritett duri </td></tr>
                            <tr><td><input type="checkbox" onChange={this.onChooseTSzDuri} /> szintelen teritett duri </td></tr>
                        </tbody>
                    </table>
                    <button onClick={this.call}>ok</button>
                </div>
            )
        }
    }

    async call(event) {
        let finalCallList = getCallList(this.state.colorId, this.state.callList);
        const target = "/call?id=" + this.props.game.player.id + "&call=" + finalCallList + "&talonid=" + this.props.talon;
        await this.setStateFromRequest(target);
    }

    onChooseColor(event) {
        this.setState({ colorId: parseInt(event.target.value) });
    }

    onChoosePassz(event) {
        this.callListHandler(0, event.target.checked);
    }

    onChoose40100(event) {
        this.callListHandler(1, event.target.checked);
    }

    onChooseUlti(event) {
        this.callListHandler(2, event.target.checked);
    }

    onChooseBetli(event) {
        this.callListHandler(3, event.target.checked);
    }

    onChooseDuri(event) {
        this.callListHandler(4, event.target.checked);
    }

    onChooseSzDuri(event) {
        this.callListHandler(5, event.target.checked);
    }

    onChoose20100(event) {
        this.callListHandler(6, event.target.checked);
    }

    onChooseTBetli(event) {
        this.callListHandler(7, event.target.checked);
    }

    onChooseTDuri(event) {
        this.callListHandler(8, event.target.checked);
    }

    onChooseTSzDuri(event) {
        this.callListHandler(9, event.target.checked);
    }

    callListHandler(value: number, state: boolean) {

        if (state) {
            this.state.callList.push(value);
        } else {
            const index = this.state.callList.indexOf(value);
            this.state.callList.splice(index, 1);
        }
    }

    async setStateFromRequest(target: string) {
        const res = await Request(target);

        if (res != null) {
            this.setState({ game: res });
        }
    }
}