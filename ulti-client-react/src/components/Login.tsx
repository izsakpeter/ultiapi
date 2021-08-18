import * as React from 'react';
import { Game } from '../model/game';
import axios, { AxiosRequestConfig } from "axios";
import { CardHandler } from '../helper/cardHandler';

export default class Login extends React.Component<{}, { username: string, gotCards: boolean, game: Game, isWrongLogin: boolean }> {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            gotCards: false,
            game: null,
            isWrongLogin: false
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeOrder = this.changeOrder.bind(this);
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    ID:
                    <input type="text" value={this.state.username} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />

                <ErrorComp isWrongLogin={this.state.isWrongLogin} />
                <ShowTable gotCards={this.state.gotCards} game={this.state.game} />
                <button onClick={this.changeOrder}>rendez</button>

            </form>
        );
    }

    handleChange(event) {
        this.setState({ username: event.target.value });
    }

    handleSubmit(event) {

        let configuration: AxiosRequestConfig = {
            timeout: 10000
        };

        configuration.baseURL = "http://localhost:8888";

        const target = `/start?id=` + this.state.username;

        axios.get<Game>(target, configuration)
            .then(respone => {
                const gameRes = respone.data;
                this.setState({ game: gameRes });
                this.setState({ gotCards: true, isWrongLogin: false });

                console.log(this.state.game.player.colorOrder + " gamegamegamegamegamegamegamegamegamegamegamegamegamegamegamegamegamegame");

            }).catch(error => {
                this.setState({ gotCards: false, isWrongLogin: true });
                console.log(error);
            });

        event.preventDefault();
    }

    changeOrder(event) {

        let configuration: AxiosRequestConfig = {
            timeout: 10000
        };

        configuration.baseURL = "http://localhost:8888";

        const target = `/order?id=` + this.state.username + `&colorOrder=` + !this.state.game.player.colorOrder;

        console.log(target);

        axios.get<Game>(target, configuration)
            .then(respone => {
                const gameRes = respone.data;
                this.setState({ game: gameRes });
                this.setState({ gotCards: true, isWrongLogin: false });

                console.log(this.state.game.player.colorOrder + " cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");

            }).catch(error => {
                this.setState({ gotCards: false, isWrongLogin: true });
                console.log(error);
            });

        event.preventDefault();
    }
}

function ErrorComp(props) {
    if (props.isWrongLogin) {
        return (
            <div>
                ERROR
            </div>
        )
    } else {
        return null;
    }
}

function ShowTable(props: any) {
    if (props.gotCards) {

        let cards = [];

        for (let i = 0; i < props.game.player.hand.length; i++) {
            cards.push(props.game.player.hand[i].id);
        }

        cards = CardHandler.getOrderedHand(cards, props.game.player.isColorOrder);

        let cardsImg = [];
        for (let i = 0; i < props.game.player.hand.length; i++) {
            cardsImg.push(<img key={"id" + i} src={CardHandler.getCardSource(props.game.player.hand[i].id)} />);
        }

        return (
            <div>
                {cardsImg}
            </div>
        )
    } else {
        return null;
    }
}