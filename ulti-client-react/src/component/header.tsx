import * as React from 'react';
import { Requests } from '../request';

export default class Header extends React.Component<{}, {value: string}> {

    constructor(props) {
        super(props);
        this.state = { value: '' };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) { this.setState({ value: event.target.value }); }
    handleSubmit(event) {
        console.log(this.state.value);
        Requests.startRequest(this.state.value).then(game => {
            console.log("player id: " + game.player.id);
        })
        .catch(error => {
            console.log("error: " + error);
        });
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    ID:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                    <input type="submit" value="Submit" />
                </form>
            </div >
        );
    }
}