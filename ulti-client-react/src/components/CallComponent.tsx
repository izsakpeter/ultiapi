import { Button, Radio, RadioGroup } from "@blueprintjs/core";
import React = require("react");
import { Call, getCallList, getCallName, getCallValue, getCallValueSum } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { PassOrJoin } from "./PassOrJoinComponent";
import { WronCallComponent } from "./WrongCallComponent";

export class CallComponent extends React.Component<{ talon: Array<number>, game: Game, hand: Array<number>, postReq: (reqObj: RequestModel) => void, clearTalon: () => void }, { colorId: number, callList: Array<number>, game: Game }>{

    constructor(props) {
        super(props);

        this.state = {
            colorId: 1,
            callList: [],
            game: null
        }

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
    }

    static getDerivedStateFromProps(props: { game: Game }, state: { colorId: number }) {

        if (props.game.previousCall.length === 0) {
            state.colorId = props.game.startingValue;
        }

        return state;
    }

    render() {

        if (this.props.game.activePlayer != this.props.game.player.id || this.props.game.playReadyToStart || this.props.game.startingValue == 0)
            return <></>;

        if (this.props.hand.length == 10 && this.props.talon.length == 0) {
            return <PassOrJoin game={this.props.game} postReq={this.props.postReq} />;
        } else if (this.props.hand.length + this.props.talon.length == 12) {

            return (
                <div>
                    <div>Előző mondás: {getCallName(this.props.game.previousCall)}, értéke: {getCallValueSum(this.props.game.previousCall)} {this.props.game.lastCallerId} által.</div>
                    <div><WronCallComponent game={this.props.game} /></div>
                    <div className={"call-table-border"}>
                        <div>
                            <Radio name="cv" label={Call.MAKK} value={Call.MAKK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.MAKK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.MAKK_ID, this.props.game)} />
                            <Radio name="cv" label={Call.ZOLD} value={Call.ZOLD_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.ZOLD_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.ZOLD_ID, this.props.game)} />
                            <Radio name="cv" label={Call.TOK} value={Call.TOK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.TOK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.TOK_ID, this.props.game)} />
                            <Radio name="cv" label={Call.PIROS} value={Call.PIROS_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.PIROS_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.PIROS_ID, this.props.game)} />
                        </div>
                        <div>
                            <table>
                                <tbody>
                                    <tr><td><input type="checkbox" name="passzCB" disabled={this.isCheckBoxDisable(Call.PASSZ_ID)} onChange={this.onChoosePassz} /> {Call.PASSZ} </td><td>{this.state.colorId * Call.PASSZ_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="szaz40CB" disabled={this.isCheckBoxDisable(Call.SZAZ40_ID)} onChange={this.onChoose40100} /> {Call.SZAZ40} </td><td>{this.state.colorId * Call.SZAZ40_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="ultiCB" disabled={this.isCheckBoxDisable(Call.ULTI_ID)} onChange={this.onChooseUlti} /> {Call.ULTI} </td><td>{this.state.colorId * Call.ULTI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="betliCB" disabled={this.isCheckBoxDisable(Call.BETLI_ID)} onChange={this.onChooseBetli} /> {Call.BETLI} </td><td>{this.state.colorId * Call.BETLI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="durCB" disabled={this.isCheckBoxDisable(Call.DURI_SZINES_ID)} onChange={this.onChooseDuri} /> {Call.DURI_SZINES} </td><td>{this.state.colorId * Call.DURI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="durSzinCB" disabled={this.isCheckBoxDisable(Call.DURI_SZINTELEN_ID)} onChange={this.onChooseSzDuri} /> {Call.DURI_SZINTELEN} </td><td>{this.state.colorId * Call.DURI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="szaz20CB" disabled={this.isCheckBoxDisable(Call.SZAZ20_ID)} onChange={this.onChoose20100} /> {Call.SZAZ20} </td><td>{this.state.colorId * Call.SZAZ20_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="betliTerCB" disabled={this.isCheckBoxDisable(Call.BETLI_TERITETT_ID)} onChange={this.onChooseTBetli} /> {Call.BETLI_TERITETT} </td><td>{this.state.colorId * Call.BETLI_TERITETT_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="durTerCB" disabled={this.isCheckBoxDisable(Call.DURI_SZINES_TERITETT_ID)} onChange={this.onChooseTDuri} /> {Call.DURI_SZINES_TERITETT} </td><td>{this.state.colorId * Call.DURI_TERITETT_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name="durSzinTerCB" disabled={this.isCheckBoxDisable(Call.DURI_SZINTELEN_TERITETT_ID)} onChange={this.onChooseTSzDuri} /> {Call.DURI_SZINTELEN_TERITETT} </td><td>{this.state.colorId * Call.DURI_TERITETT_VALUE}</td></tr>
                                </tbody>
                            </table>
                        </div>
                        <div>Mondás összértéke: {this.getCallValueSum()}</div>
                        <div className={"align-center"}><Button className={"button-ok"} onClick={this.call}>ok</Button></div>
                    </div>
                </div>
            )
        }
    }

    async call(event) {

        if (this.props.talon.length == 2) {

            let finalCallList = getCallList(this.state.colorId, this.state.callList);

            let reqObj: RequestModel = {
                dest: "call",
                id: this.props.game.player.id,
                call: finalCallList,
                talonid: this.props.talon
            }

            this.props.postReq(reqObj);
            this.props.clearTalon();

        } else {
            console.log("Nincs TALON!");
        }
    }

    callListHandler(callId: number, state: boolean, callValue: number) {

        if (state) {
            this.setState({ callList: [...this.state.callList, callId] });
        } else {
            const index = this.state.callList.indexOf(callId);
            this.state.callList.splice(index, 1);
            const tmpArray = this.state.callList;
            this.setState({ callList: tmpArray });
        }
    }

    isRadioButtonDisabled(radioId: number, game: Game): boolean {

        if (game.previousCall.length > 0)
            return false;

        return radioId != this.state.colorId;
    }

    isRadioButtonChecked(radioId: number, game: Game): boolean {

        if (game.previousCall.length > 0 && radioId == 1) {
            return true;
        } else if (game.previousCall.length === 0 && radioId === this.state.colorId) {
            return true;
        } else {
            return false;
        }
    }

    isCheckBoxDisable(id: number): boolean {
        switch (id) {
            case Call.PASSZ_ID:
                if (this.state.callList.includes(Call.SZAZ40_ID) || this.state.callList.includes(Call.ULTI_ID) || this.state.callList.includes(Call.BETLI_ID)
                    || this.state.callList.includes(Call.DURI_SZINES_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Call.SZAZ20_ID) || this.state.callList.includes(Call.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.SZAZ40_ID:
                if (this.state.callList.includes(Call.BETLI_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Call.SZAZ20_ID) || this.state.callList.includes(Call.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.ULTI_ID:
                if (this.state.callList.includes(Call.BETLI_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.BETLI_ID:
                if (this.state.callList.includes(Call.PASSZ_ID) || this.state.callList.includes(Call.SZAZ40_ID)
                    || this.state.callList.includes(Call.ULTI_ID) || this.state.callList.includes(Call.DURI_SZINES_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_ID) || this.state.callList.includes(Call.SZAZ20_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.DURI_SZINES_ID:
                if (this.state.callList.includes(Call.BETLI_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.DURI_SZINTELEN_ID:
                if (this.state.callList.includes(Call.PASSZ_ID) || this.state.callList.includes(Call.SZAZ40_ID)
                    || this.state.callList.includes(Call.ULTI_ID) || this.state.callList.includes(Call.BETLI_ID)
                    || this.state.callList.includes(Call.DURI_SZINES_ID) || this.state.callList.includes(Call.SZAZ20_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.SZAZ20_ID:
                if (this.state.callList.includes(Call.SZAZ40_ID) || this.state.callList.includes(Call.BETLI_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_ID) || this.state.callList.includes(Call.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.BETLI_TERITETT_ID:
                if (this.state.callList.includes(Call.PASSZ_ID) || this.state.callList.includes(Call.SZAZ40_ID)
                    || this.state.callList.includes(Call.ULTI_ID) || this.state.callList.includes(Call.DURI_SZINES_ID)
                    || this.state.callList.includes(Call.DURI_SZINTELEN_ID) || this.state.callList.includes(Call.SZAZ20_ID)
                    || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.DURI_SZINES_TERITETT_ID:
                if (this.state.callList.includes(Call.BETLI_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Call.DURI_SZINTELEN_TERITETT_ID:
                if (this.state.callList.includes(Call.PASSZ_ID) || this.state.callList.includes(Call.SZAZ40_ID)
                    || this.state.callList.includes(Call.ULTI_ID) || this.state.callList.includes(Call.BETLI_ID)
                    || this.state.callList.includes(Call.DURI_SZINES_ID) || this.state.callList.includes(Call.SZAZ20_ID)
                    || this.state.callList.includes(Call.BETLI_TERITETT_ID) || this.state.callList.includes(Call.DURI_SZINES_TERITETT_ID))
                    return true;
                else
                    return false;
        }

        return false;
    }

    getCallValueSum(): number {
        let res = 0;

        this.state.callList.forEach(element => {
            res += getCallValue(element);
        });

        return (res * this.state.colorId);
    }

    onChooseColor(event) {
        this.UnSelectAllCheckbox();
        this.resetValue();
        this.setState({ colorId: event.target.value });
    }

    resetValue() {
        let empty: Array<number> = [];
        this.setState({ callList: empty });
    }

    UnSelectAllCheckbox() {

        let items = document.getElementsByName('passzCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('szaz40CB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('ultiCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('betliCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('durCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('durSzinCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('szaz20CB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('betliTerCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('durTerCB');
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName('durSzinTerCB');
        (items[0] as HTMLInputElement).checked = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    onChoosePassz(event) {
        this.callListHandler(Call.PASSZ_ID, event.target.checked, Call.PASSZ_VALUE);
    }

    onChoose40100(event) {
        this.checkAndRemove(Call.PASSZ_ID, 'passzCB');
        this.callListHandler(Call.SZAZ40_ID, event.target.checked, Call.SZAZ40_VALUE);
    }

    onChooseUlti(event) {
        this.callListHandler(Call.ULTI_ID, event.target.checked, Call.ULTI_VALUE);
    }

    onChooseBetli(event) {
        this.callListHandler(Call.BETLI_ID, event.target.checked, Call.BETLI_VALUE);
    }

    onChooseDuri(event) {
        this.checkAndRemove(Call.PASSZ_ID, 'passzCB');
        this.callListHandler(Call.DURI_SZINES_ID, event.target.checked, Call.DURI_VALUE);
    }

    onChooseSzDuri(event) {
        this.callListHandler(Call.DURI_SZINTELEN_ID, event.target.checked, Call.DURI_VALUE);
    }

    onChoose20100(event) {
        this.checkAndRemove(Call.PASSZ_ID, 'passzCB');
        this.callListHandler(Call.SZAZ20_ID, event.target.checked, Call.SZAZ20_VALUE);
    }

    onChooseTBetli(event) {
        this.checkAndRemove(Call.BETLI_ID, 'betliCB');
        this.callListHandler(Call.BETLI_TERITETT_ID, event.target.checked, Call.BETLI_TERITETT_VALUE);
    }

    onChooseTDuri(event) {
        this.checkAndRemove(Call.DURI_SZINES_ID, 'durCB');
        this.callListHandler(Call.DURI_SZINES_TERITETT_ID, event.target.checked, Call.DURI_TERITETT_VALUE);
    }

    onChooseTSzDuri(event) {
        this.checkAndRemove(Call.DURI_SZINTELEN_ID, 'durSzinCB');
        this.callListHandler(Call.DURI_SZINTELEN_TERITETT_ID, event.target.checked, Call.DURI_TERITETT_VALUE);
    }

    checkAndRemove(valueId: number, cbValue: string) {

        if (this.state.callList.includes(valueId)) {
            let items = document.getElementsByName(cbValue);
            (items[0] as HTMLInputElement).checked = false;

            let tmpList: Array<number> = this.state.callList;
            const index = tmpList.indexOf(valueId);
            tmpList.splice(index, 1);
            this.setState({ callList: tmpList });
        }
    }
}