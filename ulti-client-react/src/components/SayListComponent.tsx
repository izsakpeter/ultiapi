import React from "react";
import { getSayFromMsgList } from "../helper/sayHandler";
import { GameOld } from "../model/gameOld";

interface iProps {
    game: GameOld,
}

interface iState {
}

export class SayListComponent extends React.Component<iProps, iState>{

    render() {
        return (
            <div>
                <div className="saylist-border">{this.renderSayListPanel(this.props.game)}</div>
            </div>
        )
    }


    renderSayListPanel(game: GameOld) {

        let sayList = [];

        if (game.sayMsgList != null && game.sayMsgList.length > 0) {
            for (let i = 0; i < game.sayMsgList.length; i++) {
                sayList.push(<div key={i}>{getSayFromMsgList(game.sayMsgList[i])}</div>);
            }
        }

        if (sayList.length > 0) {
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
}