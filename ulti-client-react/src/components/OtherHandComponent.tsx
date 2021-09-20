import React = require("react");
import { GetCardSource } from "../helper/cardHandler";
import { Game } from "../model/game";

export default class OtherHandComponent extends React.Component<{ game: Game }, {}> {

    render() {

        for (let j = 0; j < this.props.game.hands.length; j++) {
            if (this.props.game.player.id === this.props.game.hands[j].id) {
                let player2HandImg = [];
                let player3HandImg = [];

                for (let i = 0; i < this.props.game.hands[getIncreasedIndex(j + 1)].list.length; i++) {
                    player2HandImg.push(<img key={this.props.game.hands[getIncreasedIndex(j + 1)].list[i].uuid} src={GetCardSource(this.props.game.hands[getIncreasedIndex(j + 1)].list[i].cardId)} className="otherhand-card" />);
                }

                for (let i = 0; i < this.props.game.hands[getIncreasedIndex(j + 2)].list.length; i++) {
                    player3HandImg.push(<img key={this.props.game.hands[getIncreasedIndex(j + 2)].list[i].uuid} src={GetCardSource(this.props.game.hands[getIncreasedIndex(j + 2)].list[i].cardId)} className="otherhand-card" />);
                }

                return (
                    <div>
                        <div className="rightplayer">{player2HandImg}</div>
                        <div className="topplayer">{player3HandImg}</div>
                    </div>
                )
            }
        }

        return (
            <></>
        )
    }
}

function getIncreasedIndex(index: number) {
    if (index == 3)
        return 0;
    else if (index == 4)
        return 1;

    return index;
}

