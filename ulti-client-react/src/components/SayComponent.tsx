import { Button } from "@blueprintjs/core";
import React = require("react");
import { getAllCall, getColorIdByCallItem, have20, have40 } from "../helper/callHandler";
import { Constants } from "../helper/constants";
import { Call } from "../model/call";
import { Game } from "../model/game";
import { RequestModel } from "../model/requestModel";
import { Say } from "../model/say";

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
    isKontraDuriTerSzChecked: boolean,
    says: Array<Say>,
    isRekontraPasszChecked: boolean,
    isRekontra40100Checked: boolean,
    isRekontraUltiChecked: boolean,
    isRekontraBetliChecked: boolean,
    isRekontraDuriChecked: boolean,
    isRekontraDuriSzChecked: boolean,
    isRekontra20100Checked: boolean,
    isRekontraBetliTerChecked: boolean,
    isRekontraDuriTerChecked: boolean,
    isRekontraDuriTerSzChecked: boolean
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
            isKontraDuriTerSzChecked: false,
            says: [],
            isRekontraPasszChecked: false,
            isRekontra40100Checked: false,
            isRekontraUltiChecked: false,
            isRekontraBetliChecked: false,
            isRekontraDuriChecked: false,
            isRekontraDuriSzChecked: false,
            isRekontra20100Checked: false,
            isRekontraBetliTerChecked: false,
            isRekontraDuriTerChecked: false,
            isRekontraDuriTerSzChecked: false
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

        this.onRekontraParti = this.onRekontraParti.bind(this);
        this.onRekontra40100 = this.onRekontra40100.bind(this);
        this.onRekontraUlti = this.onRekontraUlti.bind(this);
        this.onRekontraBetli = this.onRekontraBetli.bind(this);
        this.onRekontraDuri = this.onRekontraDuri.bind(this);
        this.onRekontraDuriSz = this.onRekontraDuriSz.bind(this);
        this.onRekontra20100 = this.onRekontra20100.bind(this);
        this.onRekontraBetliTer = this.onRekontraBetliTer.bind(this);
        this.onRekontraDuriTer = this.onRekontraDuriTer.bind(this);
        this.onRekontraDuriTerSz = this.onRekontraDuriTerSz.bind(this);

        this.rekontraOkButtonHandler = this.rekontraOkButtonHandler.bind(this);
    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        if (props.game != null) {
            state = {
                ...state,
                isFirstTurn: props.game.firstTurn,
                colorId: getColorIdByCallItem(props.game.previousCall[0]),
                isSaid: props.game.player.said,
                says: props.game.says
            };
        }

        return state;
    }

    render() {

        if (!this.state.isFirstTurn) {
            return (
                <div>
                    <div>{this.rekontraPanel(this.props.game)}</div>
                </div>
            )
        } else if (this.state.isFirstTurn && this.props.game.player.hand.length === 10 && this.props.game.activePlayer == this.props.game.player.id && !this.state.isSaid) {
            return (
                <div>
                    <div><Button text="mondás" onClick={() => this.clickHandler(this.state.showPanel)} /></div>
                    <div>{this.sayPanel(this.state.showPanel, this.props.game)}</div>
                    <div>{this.rekontraPanel(this.props.game)}</div>
                </div>
            )
        } else {
            return (
                <></>
            )
        }
    }

    clickHandler(showPanel: boolean) {
        this.setState({ showPanel: !showPanel })
    }

    sayPanel(showPanel: boolean, game: Game) {
        if (showPanel) {
            let sayList = [];

            for (let i = 0; i < game.previousCall.length; i++) {
                if (Constants.LIST_PASSZ.includes(game.previousCall[i])) {
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
                    if (Constants.LIST_PASSZ.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kopa"}><input type="checkbox" name="kontraparty" onChange={this.onKontraParti} /> Kontra {Constants.PASSZ} </div>)
                    } else if (Constants.LIST_40100.includes(game.previousCall[i])) {
                        sayList.push(<div key={"ko40100"}><input type="checkbox" name="kontra40100" onChange={this.onKontra40100} /> Kontra {Constants.SZAZ40} </div>)
                    } else if (Constants.LIST_ULTI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kou"}><input type="checkbox" name="kontraulti" onChange={this.onKontraUlti} /> Kontra {Constants.ULTI} </div>)
                    } else if (Constants.LIST_BETLI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kob"}><input type="checkbox" name="kontrabetli" onChange={this.onKontraBetli} /> Kontra {Constants.BETLI} </div>)
                    } else if (Constants.LIST_DURI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kod"}><input type="checkbox" name="kontraduri" onChange={this.onKontraDuri} /> Kontra {Constants.DURI_SZINES} </div>)
                    } else if (Constants.LIST_SZ_DURI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kodsz"}><input type="checkbox" name="kontradurisz" onChange={this.onKontraDuriSz} /> Kontra {Constants.DURI_SZINTELEN} </div>)
                    } else if (Constants.LIST_20100.includes(game.previousCall[i])) {
                        sayList.push(<div key={"ko20100"}><input type="checkbox" name="kontra20100" onChange={this.onKontra20100} /> Kontra {Constants.SZAZ20} </div>)
                    } else if (Constants.LIST_TER_BETLI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kobt"}><input type="checkbox" name="kontrabetliter" onChange={this.onKontraBetliTer} /> Kontra {Constants.BETLI_TERITETT} </div>)
                    } else if (Constants.LIST_TER_DURI.includes(game.previousCall[i])) {
                        sayList.push(<div key={"kodt"}><input type="checkbox" name="kontraduriter" onChange={this.onKontraDuriTer} /> Kontra {Constants.DURI_SZINES_TERITETT} </div>)
                    } else if (Constants.LIST_TER_SZ_DURI.includes(game.previousCall[i])) {
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
        }
    }

    rekontraPanel(game: Game) {

        let rekontraList = [];

        for (let i = 0; i < game.says.length; i++) {
            if (game.lastCallerId === game.player.id && !game.says[i].ackKontra) {
                if (game.says[i].kontraPassz) {
                    rekontraList.push(<div key={"rekopa"}><input type="checkbox" name="rekontraparty" onChange={this.onRekontraParti} /> Rekontra {Constants.PASSZ} </div>)
                }

                if (game.says[i].kontra40100) {
                    rekontraList.push(<div key={"reko40100"}><input type="checkbox" name="rekontra40100" onChange={this.onRekontra40100} /> Rekontra {Constants.SZAZ40} </div>)
                }

                if (game.says[i].kontraUlti) {
                    rekontraList.push(<div key={"rekou"}><input type="checkbox" name="rekontraulti" onChange={this.onRekontraUlti} /> Rekontra {Constants.ULTI} </div>)
                }

                if (game.says[i].kontraBetli) {
                    rekontraList.push(<div key={"rekob"}><input type="checkbox" name="rekontrabetli" onChange={this.onRekontraBetli} /> Rekontra {Constants.BETLI} </div>)
                }

                if (game.says[i].kontraDuri) {
                    rekontraList.push(<div key={"rekod"}><input type="checkbox" name="rekontraduri" onChange={this.onRekontraDuri} /> Rekontra {Constants.DURI_SZINES} </div>)
                }

                if (game.says[i].kontraDuriSz) {
                    rekontraList.push(<div key={"rekodsz"}><input type="checkbox" name="rekontradurisz" onChange={this.onRekontraDuriSz} /> Rekontra {Constants.DURI_SZINTELEN} </div>)
                }

                if (game.says[i].kontra20100) {
                    rekontraList.push(<div key={"reko20100"}><input type="checkbox" name="rekontra20100" onChange={this.onRekontra20100} /> Rekontra {Constants.SZAZ20} </div>)
                }

                if (game.says[i].kontraBetliTer) {
                    rekontraList.push(<div key={"rekobt"}><input type="checkbox" name="rekontrabetliter" onChange={this.onRekontraBetliTer} /> Rekontra {Constants.BETLI_TERITETT} </div>)
                }

                if (game.says[i].kontraDuriTer) {
                    rekontraList.push(<div key={"rekodt"}><input type="checkbox" name="rekontraduriter" onChange={this.onRekontraDuriTer} /> Rekontra {Constants.DURI_SZINES_TERITETT} </div>)
                }

                if (game.says[i].kontraDuriTerSz) {
                    rekontraList.push(<div key={"rekodtsz"}><input type="checkbox" name="rekontraduritersz" onChange={this.onRekontraDuriTerSz} /> Rekontra {Constants.DURI_SZINTELEN_TERITETT} </div>)
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
    }

    sayOkButtonHandler(event) {
        if (this.state.is40Checked || this.state.is120Checked || this.state.is220Checked || this.state.is320Checked
            || this.state.isKontraPasszChecked || this.state.isKontra40100Checked || this.state.isKontraUltiChecked
            || this.state.isKontraBetliChecked || this.state.isKontraDuriChecked || this.state.isKontraDuriSzChecked
            || this.state.isKontra20100Checked || this.state.isKontraBetliTerChecked || this.state.isKontraDuriTerChecked
            || this.state.isKontraDuriTerSzChecked) {

            let needAck: boolean = false;

            if (this.state.isKontraPasszChecked || this.state.isKontra40100Checked || this.state.isKontraUltiChecked
                || this.state.isKontraBetliChecked || this.state.isKontraDuriChecked || this.state.isKontraDuriSzChecked
                || this.state.isKontra20100Checked || this.state.isKontraDuriTerChecked || this.state.isKontraDuriTerSzChecked
                || this.state.isKontraDuriTerSzChecked)
                needAck = true;

            let reqObj: RequestModel = {
                dest: "say",
                id: this.props.game.player.id,
                have40: this.state.is40Checked,
                have120: this.state.is120Checked,
                have220: this.state.is220Checked,
                have320: this.state.is320Checked,
                kontraPassz: this.state.isKontraPasszChecked,
                kontra40100: this.state.isKontra40100Checked,
                kontraUlti: this.state.isKontraUltiChecked,
                kontraBetli: this.state.isKontraBetliChecked,
                kontraDuri: this.state.isKontraDuriChecked,
                kontraDuriSz: this.state.isKontraDuriSzChecked,
                kontra20100: this.state.isKontra20100Checked,
                kontraBetliTer: this.state.isKontraBetliTerChecked,
                kontraDuriTer: this.state.isKontraDuriTerChecked,
                kontraDuriTerSz: this.state.isKontraDuriTerSzChecked,
                ackKontra: false
            }

            this.props.postReq(reqObj);
        }
    }

    rekontraOkButtonHandler(event) {
        let reqObj: RequestModel = {
            dest: "rekontra",
            id: this.props.game.player.id,
            rekontraPassz: this.state.isRekontraPasszChecked,
            rekontra40100: this.state.isRekontra40100Checked,
            rekontraUlti: this.state.isRekontraUltiChecked,
            rekontraBetli: this.state.isRekontraBetliChecked,
            rekontraDuri: this.state.isRekontraDuriChecked,
            rekontraDuriSz: this.state.isRekontraDuriSzChecked,
            rekontra20100: this.state.isRekontra20100Checked,
            rekontraBetliTer: this.state.isRekontraBetliTerChecked,
            rekontraDuriTer: this.state.isRekontraDuriTerChecked,
            rekontraDuriTerSz: this.state.isRekontraDuriTerSzChecked
        }

        this.props.postReq(reqObj);
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

    onRekontraParti(event) {
        this.setState({ isRekontraPasszChecked: event.target.checked });
    }

    onRekontra40100(event) {
        this.setState({ isRekontra40100Checked: event.target.checked });
    }

    onRekontraUlti(event) {
        this.setState({ isRekontraUltiChecked: event.target.checked });
    }

    onRekontraBetli(event) {
        this.setState({ isRekontraBetliChecked: event.target.checked });
    }

    onRekontraDuri(event) {
        this.setState({ isRekontraDuriChecked: event.target.checked });
    }

    onRekontraDuriSz(event) {
        this.setState({ isRekontraDuriSzChecked: event.target.checked });
    }

    onRekontra20100(event) {
        this.setState({ isRekontra20100Checked: event.target.checked });
    }

    onRekontraBetliTer(event) {
        this.setState({ isRekontraBetliTerChecked: event.target.checked });
    }

    onRekontraDuriTer(event) {
        this.setState({ isRekontraDuriTerChecked: event.target.checked });
    }

    onRekontraDuriTerSz(event) {
        this.setState({ isRekontraDuriTerSzChecked: event.target.checked });
    }
}