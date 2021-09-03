import React = require("react");
import { RequestModel } from "../model/requestModel";

export class LoginComponent extends React.Component <{postReq: (reqObj: RequestModel) => void},{username: string}> {

    constructor(props){
        super(props)

        this.state = {
            username: ''
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    ID:
                    <input type="text" value={this.state.username} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        )
    }

    handleChange(event) {
        this.setState({ username: event.target.value });
    }

    async handleSubmit(event) {
        event.preventDefault();

        let reqObj: RequestModel = {
            dest: "start",
            id: parseInt(this.state.username)
        }

        this.props.postReq(reqObj);
    }
}