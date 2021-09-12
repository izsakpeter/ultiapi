import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";

export default class OtherHandComponent extends React.Component<{ game: Game }, {}> {

    render() {
        if (this.props.game.player.id === this.props.game.player1Hand.id) {
            let player2HandImg = [];
            let player3HandImg = [];

            for (let i = 0; i < this.props.game.player2Hand.list.length; i++) {
                player2HandImg.push(<img key={this.props.game.player2Hand.list[i].uuid} src={GetCardSource(this.props.game.player2Hand.list[i].cardId)} className="otherhand-card" />);
            }

            for (let i = 0; i < this.props.game.player3Hand.list.length; i++) {
                player3HandImg.push(<img key={this.props.game.player3Hand.list[i].uuid} src={GetCardSource(this.props.game.player3Hand.list[i].cardId)} className="otherhand-card" />);
            }

            return (
                <div>
                    <div className="rightplayer">{player2HandImg}</div>
                    <div className="topplayer">{player3HandImg}</div>
                </div>

            )
        } else if (this.props.game.player.id === this.props.game.player2Hand.id) {
            let player3HandImg = [];
            let player1HandImg = [];

            for (let i = 0; i < this.props.game.player1Hand.list.length; i++) {
                player1HandImg.push(<img key={this.props.game.player1Hand.list[i].uuid} src={GetCardSource(this.props.game.player1Hand.list[i].cardId)} className="otherhand-card" />);
            }

            for (let i = 0; i < this.props.game.player3Hand.list.length; i++) {
                player3HandImg.push(<img key={this.props.game.player3Hand.list[i].uuid} src={GetCardSource(this.props.game.player3Hand.list[i].cardId)} className="otherhand-card" />);
            }

            return (
                <div>
                    <div className="rightplayer">{player3HandImg}</div>
                    <div className="topplayer">{player1HandImg}</div>
                </div>

            )
        } else if (this.props.game.player.id === this.props.game.player3Hand.id) {
            let player1HandImg = [];
            let player2HandImg = [];

            for (let i = 0; i < this.props.game.player2Hand.list.length; i++) {
                player2HandImg.push(<img key={this.props.game.player2Hand.list[i].uuid} src={GetCardSource(this.props.game.player2Hand.list[i].cardId)} className="otherhand-card" />);
            }

            for (let i = 0; i < this.props.game.player1Hand.list.length; i++) {
                player1HandImg.push(<img key={this.props.game.player1Hand.list[i].uuid} src={GetCardSource(this.props.game.player1Hand.list[i].cardId)} className="otherhand-card" />);
            }

            return (
                <div>
                    <div className="rightplayer">{player1HandImg}</div>
                    <div className="topplayer">{player2HandImg}</div>
                </div>
            )
        }

        return (
            <></>
        )
    }
}

