import { Button } from "@blueprintjs/core";
import React = require("react");
import { getColorIdByCallItem, have20, have40 } from "../helper/callHandler";
import { Constants } from "../helper/constants";
import { getSayFromMsgList } from "../helper/sayHandler";
import { Call } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";

interface iProps {
    game: Game,
    postReq: (reqObj: RequestModel) => void
}

interface iState {
    showPanel: boolean,
    is40Checked: boolean,
    is120Checked: boolean,
    is220Checked: boolean,
    is320Checked: boolean,
    colorId: number,
    isFirstTurn: boolean,
    isSaid: boolean,
    isKontraPasszChecked: boolean,
    isKontra40100Checked: boolean,
    isKontraUltiChecked: boolean,
    isKontraBetliChecked: boolean,
    isKontraDuriChecked: boolean,
    isKontraDuriSzChecked: boolean,
    isKontra20100Checked: boolean,
    isKontraBetliTerChecked: boolean,
    isKontraDuriTerChecked: boolean,
    isKontraDuriTerSzChecked: boolean
}

export class SayComponent extends React.Component<iProps, iState>{

    constructor(props) {
        super(props);

        this.state = {
            showPanel: false,
            is40Checked: false,
            is120Checked: false,
            is220Checked: false,
            is320Checked: false,
            colorId: 0,
            isFirstTurn: false,
            isSaid: true,
            isKontraPasszChecked: false,
            isKontra40100Checked: false,
            isKontraUltiChecked: false,
            isKontraBetliChecked: false,
            isKontraDuriChecked: false,
            isKontraDuriSzChecked: false,
            isKontra20100Checked: false,
            isKontraBetliTerChecked: false,
            isKontraDuriTerChecked: false,
            isKontraDuriTerSzChecked: false
        }

        this.onChoose40 = this.onChoose40.bind(this);
        this.onChoose120 = this.onChoose120.bind(this);
        this.onChoose220 = this.onChoose220.bind(this);
        this.onChoose320 = this.onChoose320.bind(this);
        this.onKontraParti = this.onKontraParti.bind(this);
        this.onKontra40100 = this.onKontra40100.bind(this);
        this.onKontraUlti = this.onKontraUlti.bind(this);
        this.onKontraBetli = this.onKontraBetli.bind(this);
        this.onKontraDuri = this.onKontraDuri.bind(this);
        this.onKontraDuriSz = this.onKontraDuriSz.bind(this);
        this.onKontra20100 = this.onKontra20100.bind(this);
        this.onKontraBetliTer = this.onKontraBetliTer.bind(this);
        this.onKontraDuriTer = this.onKontraDuriTer.bind(this);
        this.onKontraDuriTerSz = this.onKontraDuriTerSz.bind(this);
        this.sayOkButtonHandler = this.sayOkButtonHandler.bind(this);
        this.rekontraOkButtonHandler = this.rekontraOkButtonHandler.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        if (props.game != null && props.game.playReadyToStart) {
            state = {
                ...state,
                isFirstTurn: props.game.firstTurn,
                colorId: getColorIdByCallItem(props.game.previousCall[0].callId),
                isSaid: props.game.player.said
            };
        }

        return state;
    }

    render() {
        if (this.state.isFirstTurn && this.props.game.player.hand.length === 10 && this.props.game.activePlayer == this.props.game.player.id && !this.state.isSaid) {
            return (
                <div>
                    <div><Button text="mondás" onClick={() => this.clickHandler(this.state.showPanel)} /></div>
                    <div>{this.renderSayPanel(this.state.showPanel, this.props.game)}</div>
                    <div className={"sayMsg-poz"}>{this.renderSayListPanel(this.props.game)}</div>
                </div>
            )
        } else {
            return (
                <div>
                    <div>{this.renderRekontraPanel(this.props.game)}</div>
                    <div className={"sayMsg-poz"}>{this.renderSayListPanel(this.props.game)}</div>
                </div>
            )
        }
    }

    clickHandler(showPanel: boolean) {
        this.setState({ showPanel: !showPanel })
    }

    renderSayPanel(showPanel: boolean, game: Game) {
        if (showPanel) {

            let sayList = [];

            for (let i = 0; i < game.previousCall.length; i++) {

                if (Constants.LIST_PASSZ.includes(game.previousCall[i].callId)) {

                    sayList.push(
                        <div key={"20-40"}>
                            <div><input type="checkbox" name="40" disabled={this.disable40()} onChange={this.onChoose40} /> 40 </div>
                            <div><input type="checkbox" name="120" disabled={this.disable20(1)} onChange={this.onChoose120} /> 20 </div>
                            <div><input type="checkbox" name="220" disabled={this.disable20(2)} onChange={this.onChoose220} /> 2x20</div>
                            <div><input type="checkbox" name="320" disabled={this.disable20(3)} onChange={this.onChoose320} /> 3x20</div>
                        </div>)
                }
            }

            if (game.lastCallerId != game.activePlayer) {
                for (let i = 0; i < game.previousCall.length; i++) {
                    if (Constants.LIST_PASSZ.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kopa"}><input type="checkbox" name="kontraparty" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontraParti} /> Kontra {Constants.PASSZ} </div>)
                    } else if (Constants.LIST_40100.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"ko40100"}><input type="checkbox" name="kontra40100" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontra40100} /> Kontra {Constants.SZAZ40} </div>)
                    } else if (Constants.LIST_ULTI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kou"}><input type="checkbox" name="kontraulti" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontraUlti} /> Kontra {Constants.ULTI} </div>)
                    } else if (Constants.LIST_BETLI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kob"}><input type="checkbox" name="kontrabetli" onChange={this.onKontraBetli} /> Kontra {Constants.BETLI} </div>)
                    } else if (Constants.LIST_DURI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kod"}><input type="checkbox" name="kontraduri" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontraDuri} /> Kontra {Constants.DURI_SZINES} </div>)
                    } else if (Constants.LIST_SZ_DURI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kodsz"}><input type="checkbox" name="kontradurisz" onChange={this.onKontraDuriSz} /> Kontra {Constants.DURI_SZINTELEN} </div>)
                    } else if (Constants.LIST_20100.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"ko20100"}><input type="checkbox" name="kontra20100" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontra20100} /> Kontra {Constants.SZAZ20} </div>)
                    } else if (Constants.LIST_TER_BETLI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kobt"}><input type="checkbox" name="kontrabetliter" onChange={this.onKontraBetliTer} /> Kontra {Constants.BETLI_TERITETT} </div>)
                    } else if (Constants.LIST_TER_DURI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kodt"}><input type="checkbox" name="kontraduriter" disabled={this.isDisabledCb(game.previousCall[i])} defaultChecked={this.isCheckedCb(game.previousCall[i])} onChange={this.onKontraDuriTer} /> Kontra {Constants.DURI_SZINES_TERITETT} </div>)
                    } else if (Constants.LIST_TER_SZ_DURI.includes(game.previousCall[i].callId)) {
                        sayList.push(<div key={"kodtsz"}><input type="checkbox" name="kontraduritersz" onChange={this.onKontraDuriTerSz} /> Kontra {Constants.DURI_SZINTELEN_TERITETT} </div>)
                    }
                }
            }

            if (sayList.length > 0)
                sayList.push(<div key={"sayokb"}><Button text="ok" onClick={this.sayOkButtonHandler} /></div>);

            return (
                <div>
                    {sayList}
                </div>
            )

        } else {
            return (
                <></>
            )
        }
    }

    sayOkButtonHandler(event) {
        if (this.state.is40Checked || this.state.is120Checked || this.state.is220Checked || this.state.is320Checked) {

            let reqObj: RequestModel = {
                dest: "sayparti",
                id: this.props.game.player.id,
                have40: this.state.is40Checked,
                have120: this.state.is120Checked,
                have220: this.state.is220Checked,
                have320: this.state.is320Checked
            }

            this.props.postReq(reqObj);
        }

        if (this.state.isKontraPasszChecked || this.state.isKontra40100Checked || this.state.isKontraUltiChecked
            || this.state.isKontraBetliChecked || this.state.isKontraDuriChecked || this.state.isKontraDuriSzChecked
            || this.state.isKontra20100Checked || this.state.isKontraBetliTerChecked || this.state.isKontraDuriTerChecked
            || this.state.isKontraDuriTerSzChecked) {

            let reqObj: RequestModel = {
                dest: "saykontra",
                id: this.props.game.player.id,
                kontraId: 1,
                kontraPassz: this.state.isKontraPasszChecked,
                kontra40100: this.state.isKontra40100Checked,
                kontraUlti: this.state.isKontraUltiChecked,
                kontraBetli: this.state.isKontraBetliChecked,
                kontraDuri: this.state.isKontraDuriChecked,
                kontraDuriSz: this.state.isKontraDuriSzChecked,
                kontra20100: this.state.isKontra20100Checked,
                kontraBetliTer: this.state.isKontraBetliTerChecked,
                kontraDuriTer: this.state.isKontraDuriTerChecked,
                kontraDuriTerSz: this.state.isKontraDuriTerSzChecked
            }

            this.props.postReq(reqObj);
        }
    }

    renderRekontraPanel(game: Game) {

        let showPanel: boolean = false;

        if (game.activePlayer === game.player.id && game.lastCallerId === game.player.id && game.previousCall[0].kontra != null) {
            for (let i = 0; i < game.previousCall.length; i++) {
                for (let j = 0; j < game.previousCall[i].kontra.length; j++) {
                    if (game.previousCall[i].kontra[j].kontra.said && (game.previousCall[i].kontra[j].kontra.ackBy === -1)) {
                        showPanel = true;
                    }
                }
            }
        }

        if (showPanel) {
            let rekontraList = [];

            for (let i = 0; i < game.previousCall.length; i++) {
                for (let j = 0; j < game.previousCall[i].kontra.length; j++) {
                    if (game.previousCall[i].kontra[j].kontra.said && (game.previousCall[i].kontra[j].kontra.said)) {
                        if (Constants.LIST_PASSZ.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkopa"}><input type="checkbox" name="kontraparty" onChange={this.onKontraParti} /> Rekontra {Constants.PASSZ} </div>)
                        } else if (Constants.LIST_40100.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rko40100"}><input type="checkbox" name="kontra40100" onChange={this.onKontra40100} /> Rekontra {Constants.SZAZ40} </div>)
                        } else if (Constants.LIST_ULTI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkou"}><input type="checkbox" name="kontraulti" onChange={this.onKontraUlti} /> Rekontra {Constants.ULTI} </div>)
                        } else if (Constants.LIST_BETLI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkob"}><input type="checkbox" name="kontrabetli" onChange={this.onKontraBetli} /> Rekontra {Constants.BETLI} </div>)
                        } else if (Constants.LIST_DURI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkod"}><input type="checkbox" name="kontraduri" onChange={this.onKontraDuri} /> Rekontra {Constants.DURI_SZINES} </div>)
                        } else if (Constants.LIST_SZ_DURI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkodsz"}><input type="checkbox" name="kontradurisz" onChange={this.onKontraDuriSz} /> Rekontra {Constants.DURI_SZINTELEN} </div>)
                        } else if (Constants.LIST_20100.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rko20100"}><input type="checkbox" name="kontra20100" onChange={this.onKontra20100} /> Rekontra {Constants.SZAZ20} </div>)
                        } else if (Constants.LIST_TER_BETLI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkobt"}><input type="checkbox" name="kontrabetliter" onChange={this.onKontraBetliTer} /> Rekontra {Constants.BETLI_TERITETT} </div>)
                        } else if (Constants.LIST_TER_DURI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkodt"}><input type="checkbox" name="kontraduriter" onChange={this.onKontraDuriTer} /> Rekontra {Constants.DURI_SZINES_TERITETT} </div>)
                        } else if (Constants.LIST_TER_SZ_DURI.includes(game.previousCall[i].callId)) {
                            rekontraList.push(<div key={"rkodtsz"}><input type="checkbox" name="kontraduritersz" onChange={this.onKontraDuriTerSz} /> Rekontra {Constants.DURI_SZINTELEN_TERITETT} </div>)
                        }
                    }
                }
            }

            if (rekontraList.length > 0)
                rekontraList.push(<div key={"rekokb"}><Button text="ok" onClick={this.rekontraOkButtonHandler} /></div>);

            return (
                <div>
                    {rekontraList}
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    rekontraOkButtonHandler(event) {
        let reqObj: RequestModel = {
            dest: "saykontra",
            id: this.props.game.player.id,
            kontraId: 2,
            kontraPassz: this.state.isKontraPasszChecked,
            kontra40100: this.state.isKontra40100Checked,
            kontraUlti: this.state.isKontraUltiChecked,
            kontraBetli: this.state.isKontraBetliChecked,
            kontraDuri: this.state.isKontraDuriChecked,
            kontraDuriSz: this.state.isKontraDuriSzChecked,
            kontra20100: this.state.isKontra20100Checked,
            kontraBetliTer: this.state.isKontraBetliTerChecked,
            kontraDuriTer: this.state.isKontraDuriTerChecked,
            kontraDuriTerSz: this.state.isKontraDuriTerSzChecked
        }

        this.props.postReq(reqObj);
    }

    renderSayListPanel(game: Game) {

        let sayList = [];

        if (game.sayMsgList != null && game.sayMsgList.length > 0) {
            for (let i = 0; i < game.sayMsgList.length; i++) {
                sayList.push(<div key={i}>{getSayFromMsgList(game.sayMsgList[i])}</div>);
            }
        }

        if (sayList.length > 0) {
            return (
                <div className={"sayMsg-border"}>
                    {sayList}
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    disable40(): boolean {
        return have40(this.state.colorId, this.props.game)
    }

    disable20(counter: number): boolean {
        return !(have20(this.state.colorId, this.props.game) === counter);
    }

    onChoose40(event) {
        this.setState({ is40Checked: event.target.checked });
    }

    onChoose120(event) {
        this.setState({ is120Checked: event.target.checked });
    }

    onChoose220(event) {
        this.setState({ is220Checked: event.target.checked });
    }

    onChoose320(event) {
        this.setState({ is320Checked: event.target.checked });
    }

    onKontraParti(event) {
        this.setState({ isKontraPasszChecked: event.target.checked });
    }

    onKontra40100(event) {
        this.setState({ isKontra40100Checked: event.target.checked });
    }

    onKontraUlti(event) {
        this.setState({ isKontraUltiChecked: event.target.checked });
    }

    onKontraBetli(event) {
        this.setState({ isKontraBetliChecked: event.target.checked });
    }

    onKontraDuri(event) {
        this.setState({ isKontraDuriChecked: event.target.checked });
    }

    onKontraDuriSz(event) {
        this.setState({ isKontraDuriSzChecked: event.target.checked });
    }

    onKontra20100(event) {
        this.setState({ isKontra20100Checked: event.target.checked });
    }

    onKontraBetliTer(event) {
        this.setState({ isKontraBetliTerChecked: event.target.checked });
    }

    onKontraDuriTer(event) {
        this.setState({ isKontraDuriTerChecked: event.target.checked });
    }

    onKontraDuriTerSz(event) {
        this.setState({ isKontraDuriTerSzChecked: event.target.checked });
    }

    isDisabledCb(call: Call): boolean {

        let isDisabled: boolean = false;

        for (let i = 0; i < call.kontra.length; i++) {
            if (call.kontra[i].kontra.said)
                isDisabled = true;
        }

        return isDisabled;
    }

    isCheckedCb(call: Call): boolean {

        let isChecked: boolean = false;

        for (let i = 0; i < call.kontra.length; i++) {
            if (call.kontra[i].kontra.said)
                isChecked = true;
        }

        return isChecked;
    }
}