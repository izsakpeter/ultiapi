import { Button, Radio, RadioGroup } from "@blueprintjs/core";
import React = require("react");
import { Constants } from "../helper/constants";
import { getCallList, getCallNameList, getCallValue, getCallValueSum, isBluff4020 } from "../helper/callHandler";
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
                    <div className={"call-table-border"}>
                        <div>
                            {getCallValueSum(this.props.game.previousCall) === 0 ? "" : <div>Előző mondás: {getCallNameList(this.props.game.previousCall)}, értéke: {getCallValueSum(this.props.game.previousCall)} {this.props.game.lastCallerId} által.</div>}
                            <div><WronCallComponent game={this.props.game} /></div>
                            <Radio name="cv" label={Constants.MAKK} value={Constants.MAKK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Constants.MAKK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Constants.MAKK_ID, this.props.game)} />
                            <Radio name="cv" label={Constants.ZOLD} value={Constants.ZOLD_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Constants.ZOLD_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Constants.ZOLD_ID, this.props.game)} />
                            <Radio name="cv" label={Constants.TOK} value={Constants.TOK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Constants.TOK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Constants.TOK_ID, this.props.game)} />
                            <Radio name="cv" label={Constants.PIROS} value={Constants.PIROS_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Constants.PIROS_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Constants.PIROS_ID, this.props.game)} />
                        </div>
                        <div>
                            <table>
                                <tbody>
                                    <tr><td><input type="checkbox" name={Constants.PASSZ_CB} disabled={this.isCheckBoxDisable(Constants.PASSZ_ID)} onChange={this.onChoosePassz} /> {Constants.PASSZ} </td><td>{this.state.colorId * Constants.PASSZ_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.SZAZ40_CB} disabled={this.isCheckBoxDisable(Constants.SZAZ40_ID)} onChange={this.onChoose40100} /> {Constants.SZAZ40} </td><td>{this.state.colorId * Constants.SZAZ40_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.ULTI_CB} disabled={this.isCheckBoxDisable(Constants.ULTI_ID)} onChange={this.onChooseUlti} /> {Constants.ULTI} </td><td>{this.state.colorId * Constants.ULTI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.BETLI_CB} disabled={this.isCheckBoxDisable(Constants.BETLI_ID)} onChange={this.onChooseBetli} /> {Constants.BETLI} </td><td>{this.state.colorId * Constants.BETLI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.DURI_CB} disabled={this.isCheckBoxDisable(Constants.DURI_SZINES_ID)} onChange={this.onChooseDuri} /> {Constants.DURI_SZINES} </td><td>{this.state.colorId * Constants.DURI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.DURI_SZINTELEN_CB} disabled={this.isCheckBoxDisable(Constants.DURI_SZINTELEN_ID)} onChange={this.onChooseSzDuri} /> {Constants.DURI_SZINTELEN} </td><td>{this.state.colorId * Constants.DURI_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.SZAZ20_CB} disabled={this.isCheckBoxDisable(Constants.SZAZ20_ID)} onChange={this.onChoose20100} /> {Constants.SZAZ20} </td><td>{this.state.colorId * Constants.SZAZ20_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.BETLI_TERITETT_CB} disabled={this.isCheckBoxDisable(Constants.BETLI_TERITETT_ID)} onChange={this.onChooseTBetli} /> {Constants.BETLI_TERITETT} </td><td>{this.state.colorId * Constants.BETLI_TERITETT_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.DURI_TERITETT_CB} disabled={this.isCheckBoxDisable(Constants.DURI_SZINES_TERITETT_ID)} onChange={this.onChooseTDuri} /> {Constants.DURI_SZINES_TERITETT} </td><td>{this.state.colorId * Constants.DURI_TERITETT_VALUE}</td></tr>
                                    <tr><td><input type="checkbox" name={Constants.DURI_SZINTELEN_TERITETT_CB} disabled={this.isCheckBoxDisable(Constants.DURI_SZINTELEN_TERITETT_ID)} onChange={this.onChooseTSzDuri} /> {Constants.DURI_SZINTELEN_TERITETT} </td><td>{this.state.colorId * Constants.DURI_TERITETT_VALUE}</td></tr>
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
                talonid: this.props.talon,
                bluff4020: isBluff4020(finalCallList, this.state.colorId, this.props.game)
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
            case Constants.PASSZ_ID:
                if (this.state.callList.includes(Constants.SZAZ40_ID) || this.state.callList.includes(Constants.ULTI_ID) || this.state.callList.includes(Constants.BETLI_ID)
                    || this.state.callList.includes(Constants.DURI_SZINES_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Constants.SZAZ20_ID) || this.state.callList.includes(Constants.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.SZAZ40_ID:
                if (this.state.callList.includes(Constants.BETLI_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Constants.SZAZ20_ID) || this.state.callList.includes(Constants.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.ULTI_ID:
                if (this.state.callList.includes(Constants.BETLI_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.BETLI_ID:
                if (this.state.callList.includes(Constants.PASSZ_ID) || this.state.callList.includes(Constants.SZAZ40_ID)
                    || this.state.callList.includes(Constants.ULTI_ID) || this.state.callList.includes(Constants.DURI_SZINES_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_ID) || this.state.callList.includes(Constants.SZAZ20_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.DURI_SZINES_ID:
                if (this.state.callList.includes(Constants.BETLI_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.DURI_SZINTELEN_ID:
                if (this.state.callList.includes(Constants.PASSZ_ID) || this.state.callList.includes(Constants.SZAZ40_ID)
                    || this.state.callList.includes(Constants.ULTI_ID) || this.state.callList.includes(Constants.BETLI_ID)
                    || this.state.callList.includes(Constants.DURI_SZINES_ID) || this.state.callList.includes(Constants.SZAZ20_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.SZAZ20_ID:
                if (this.state.callList.includes(Constants.SZAZ40_ID) || this.state.callList.includes(Constants.BETLI_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_ID) || this.state.callList.includes(Constants.BETLI_TERITETT_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.BETLI_TERITETT_ID:
                if (this.state.callList.includes(Constants.PASSZ_ID) || this.state.callList.includes(Constants.SZAZ40_ID)
                    || this.state.callList.includes(Constants.ULTI_ID) || this.state.callList.includes(Constants.DURI_SZINES_ID)
                    || this.state.callList.includes(Constants.DURI_SZINTELEN_ID) || this.state.callList.includes(Constants.SZAZ20_ID)
                    || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.DURI_SZINES_TERITETT_ID:
                if (this.state.callList.includes(Constants.BETLI_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINTELEN_TERITETT_ID))
                    return true;
                else
                    return false;
            case Constants.DURI_SZINTELEN_TERITETT_ID:
                if (this.state.callList.includes(Constants.PASSZ_ID) || this.state.callList.includes(Constants.SZAZ40_ID)
                    || this.state.callList.includes(Constants.ULTI_ID) || this.state.callList.includes(Constants.BETLI_ID)
                    || this.state.callList.includes(Constants.DURI_SZINES_ID) || this.state.callList.includes(Constants.SZAZ20_ID)
                    || this.state.callList.includes(Constants.BETLI_TERITETT_ID) || this.state.callList.includes(Constants.DURI_SZINES_TERITETT_ID))
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

        let items = document.getElementsByName(Constants.PASSZ_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.SZAZ40_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.ULTI_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.BETLI_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.DURI_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.DURI_SZINTELEN_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.SZAZ20_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.BETLI_TERITETT_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.DURI_TERITETT_CB);
        (items[0] as HTMLInputElement).checked = false;

        items = document.getElementsByName(Constants.DURI_SZINTELEN_TERITETT_CB);
        (items[0] as HTMLInputElement).checked = false;
    }

    onChoosePassz(event) {
        this.callListHandler(Constants.PASSZ_ID, event.target.checked, Constants.PASSZ_VALUE);
    }

    onChoose40100(event) {
        this.checkAndRemove(Constants.PASSZ_ID, Constants.PASSZ_CB);
        this.ultiCheckAddPasz(event.target.checked);
        this.callListHandler(Constants.SZAZ40_ID, event.target.checked, Constants.SZAZ40_VALUE);
    }

    onChooseUlti(event) {

        if (this.state.callList.length === 0) {
            let items = document.getElementsByName(Constants.PASSZ_CB);
            (items[0] as HTMLInputElement).checked = true;

            let tmpList: Array<number> = this.state.callList;
            tmpList.push(Constants.PASSZ_ID);
            this.setState({ callList: tmpList });
        }

        if (!event.target.checked && this.state.callList.length === 2 && this.state.callList.includes(Constants.PASSZ_ID)) {
            let items = document.getElementsByName(Constants.PASSZ_CB);
            (items[0] as HTMLInputElement).checked = false;

            let tmpList: Array<number> = this.state.callList;
            const index = tmpList.indexOf(Constants.PASSZ_ID);
            tmpList.splice(index, 1);
            this.setState({ callList: tmpList });
        }

        this.callListHandler(Constants.ULTI_ID, event.target.checked, Constants.ULTI_VALUE);
    }

    onChooseBetli(event) {
        this.callListHandler(Constants.BETLI_ID, event.target.checked, Constants.BETLI_VALUE);
    }

    onChooseDuri(event) {
        this.checkAndRemove(Constants.PASSZ_ID, Constants.PASSZ_CB);
        this.ultiCheckAddPasz(event.target.checked);
        this.callListHandler(Constants.DURI_SZINES_ID, event.target.checked, Constants.DURI_VALUE);
    }

    onChooseSzDuri(event) {
        this.callListHandler(Constants.DURI_SZINTELEN_ID, event.target.checked, Constants.DURI_VALUE);
    }

    onChoose20100(event) {
        this.checkAndRemove(Constants.PASSZ_ID, Constants.PASSZ_CB);
        this.ultiCheckAddPasz(event.target.checked);
        this.callListHandler(Constants.SZAZ20_ID, event.target.checked, Constants.SZAZ20_VALUE);
    }

    onChooseTBetli(event) {
        this.checkAndRemove(Constants.BETLI_ID, Constants.BETLI_CB);
        this.callListHandler(Constants.BETLI_TERITETT_ID, event.target.checked, Constants.BETLI_TERITETT_VALUE);
    }

    onChooseTDuri(event) {
        this.checkAndRemove(Constants.PASSZ_ID, Constants.PASSZ_CB);
        this.checkAndRemove(Constants.DURI_SZINES_ID, Constants.DURI_CB);
        this.ultiCheckAddPasz(event.target.checked);
        this.callListHandler(Constants.DURI_SZINES_TERITETT_ID, event.target.checked, Constants.DURI_TERITETT_VALUE);
    }

    onChooseTSzDuri(event) {
        this.checkAndRemove(Constants.DURI_SZINTELEN_ID, Constants.DURI_SZINTELEN_CB);
        this.callListHandler(Constants.DURI_SZINTELEN_TERITETT_ID, event.target.checked, Constants.DURI_TERITETT_VALUE);
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

    ultiCheckAddPasz(checked: boolean) {
        if (!checked && this.state.callList.length === 2 && this.state.callList.includes(Constants.ULTI_ID)) {

            let items = document.getElementsByName(Constants.PASSZ_CB);
            (items[0] as HTMLInputElement).checked = true;

            let tmpList: Array<number> = this.state.callList;
            tmpList.push(Constants.PASSZ_ID);
            this.setState({ callList: tmpList });
        }
    }
}