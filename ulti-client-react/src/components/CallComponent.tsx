import { Button, Radio, RadioGroup } from "@blueprintjs/core";
import React = require("react");
import { Call, getCallList } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { PassOrJoin } from "./PassOrJoinComponent";
import { WronCallComponent } from "./WrongCallComponent";

export class CallComponent extends React.Component<{ talon: Array<number>, game: Game, hand: Array<number>, postReq: (reqObj: RequestModel) => void, clearTalon: () => void }, { colorId: number, callList: Array<number>, game: Game }>{

    constructor(props) {
        super(props);

        this.state = {
            colorId: 0,
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

    static getDerivedStateFromProps(props: { game: Game }, state: { colorId: number, callList: Array<number> }) {
        state.colorId = props.game.startingValue;

        if (props.game.previousCall.length ===0)
            state.callList =[0];

        return state;
    }

    render() {

        if (this.props.game.activePlayer != this.props.game.player.id || this.props.game.playReadyToStart || this.props.game.startingValue == 0)
            return <></>;

        if (this.props.hand.length == 10 && this.props.talon.length == 0) {
            return <PassOrJoin game={this.props.game} postReq={this.props.postReq} />;
        } else if (this.props.hand.length + this.props.talon.length == 12) {

            return (
                <>

                    <div className={"call-table-border"}>
                        <WronCallComponent game={this.props.game} />
                        <Radio name="cv" label="MAKK" value={Call.MAKK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.MAKK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.MAKK_ID, this.props.game)} />
                        <Radio name="cv" label="ZOLD" value={Call.ZOLD_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.ZOLD_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.ZOLD_ID, this.props.game)} />
                        <Radio name="cv" label="TOK" value={Call.TOK_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.TOK_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.TOK_ID, this.props.game)} />
                        <Radio name="cv" label="PIROS" value={Call.PIROS_ID} onClick={this.onChooseColor} defaultChecked={this.isRadioButtonChecked(Call.PIROS_ID, this.props.game)} disabled={this.isRadioButtonDisabled(Call.PIROS_ID, this.props.game)} />
                        <table>
                            <tbody>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.PASSZ_ID)} disabled={this.isCheckBoxDisable(Call.PASSZ_ID)} onChange={this.onChoosePassz} /> passz </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.SZAZ40_ID)} disabled={this.isCheckBoxDisable(Call.SZAZ40_ID)} onChange={this.onChoose40100} /> 40-100 </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.ULTI_ID)} disabled={this.isCheckBoxDisable(Call.ULTI_ID)} onChange={this.onChooseUlti} /> ulti </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.BETLI_ID)} disabled={this.isCheckBoxDisable(Call.BETLI_ID)} onChange={this.onChooseBetli} /> betli </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.DURI_SZINES_ID)} disabled={this.isCheckBoxDisable(Call.DURI_SZINES_ID)} onChange={this.onChooseDuri} /> duri </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.DURI_SZINTELEN_ID)} disabled={this.isCheckBoxDisable(Call.DURI_SZINTELEN_ID)} onChange={this.onChooseSzDuri} /> szintelen duri </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.SZAZ20_ID)} disabled={this.isCheckBoxDisable(Call.SZAZ20_ID)} onChange={this.onChoose20100} /> 20-100 </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.BETLI_TERITETT_ID)} disabled={this.isCheckBoxDisable(Call.BETLI_TERITETT_ID)} onChange={this.onChooseTBetli} /> teritett betli </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.DURI_SZINES_TERITETT_ID)} disabled={this.isCheckBoxDisable(Call.DURI_SZINES_TERITETT_ID)} onChange={this.onChooseTDuri} /> teritett duri </td></tr>
                                <tr><td><input type="checkbox" defaultChecked={this.isCheckboxCheck(Call.DURI_SZINTELEN_TERITETT_ID)} disabled={this.isCheckBoxDisable(Call.DURI_SZINTELEN_TERITETT_ID)} onChange={this.onChooseTSzDuri} /> szintelen teritett duri </td></tr>
                            </tbody>
                        </table>
                        <div className={"align-center"}><Button className={"button-ok"} onClick={this.call}>ok</Button></div>
                    </div></>
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

    onChooseColor(event) {
        this.setState({ colorId: parseInt(event.target.value) });
    }

    onChoosePassz(event) {
        this.callListHandler(Call.PASSZ_ID, event.target.checked);
    }

    onChoose40100(event) {
        this.callListHandler(Call.SZAZ40_ID, event.target.checked);
    }

    onChooseUlti(event) {
        this.callListHandler(Call.ULTI_ID, event.target.checked);
    }

    onChooseBetli(event) {
        this.callListHandler(Call.BETLI_ID, event.target.checked);
    }

    onChooseDuri(event) {
        this.callListHandler(Call.DURI_SZINES_ID, event.target.checked);
    }

    onChooseSzDuri(event) {
        this.callListHandler(Call.DURI_SZINTELEN_ID, event.target.checked);
    }

    onChoose20100(event) {
        this.callListHandler(Call.SZAZ20_ID, event.target.checked);
    }

    onChooseTBetli(event) {
        this.callListHandler(Call.BETLI_TERITETT_ID, event.target.checked);
    }

    onChooseTDuri(event) {
        this.callListHandler(Call.DURI_SZINES_TERITETT_ID, event.target.checked);
    }

    onChooseTSzDuri(event) {
        this.callListHandler(Call.DURI_SZINTELEN_TERITETT_ID, event.target.checked);
    }

    callListHandler(value: number, state: boolean) {

        if (state) {
            this.setState({ callList: [...this.state.callList, value] });
        } else {
            const index = this.state.callList.indexOf(value);
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

    isCheckboxCheck(id: number): boolean {

        if (this.props.game.previousCall.length === 0 && id === Call.PASSZ_ID)
            return true;

        return this.state.callList.includes(id);
    }
}