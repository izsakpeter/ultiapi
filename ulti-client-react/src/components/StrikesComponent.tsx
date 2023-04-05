import React from "react";
import { Button, Modal } from "react-bootstrap";
import { getCardSource } from "../helper/cardHandler";
import { Strike } from "../model/strike";

interface iProps {
    strikes: Array<Strike>
}

interface iState {
    isShow: boolean
}

export class StrikesComponent extends React.Component<iProps, iState>{

    constructor(props: any) {
        super(props);

        this.state = {
            isShow: false
        }

        this.handleShow = this.handleShow.bind(this);

    }

    static getDerivedStateFromProps(props: iProps, state: iState) {
        return state;
    }

    render() {

        let strikes1Col = [];
        let strikes2Col = [];
        let colLength: number = 5;

        if (this.props.strikes.length > colLength) {

            let tmp = [];

            for (let j = 0; j < colLength; j++) {
                tmp.push(
                    <div key={this.props.strikes[j].round}>
                        kör:{this.props.strikes[j].round}
                        <img alt="card" src={getCardSource(this.props.strikes[j].card1Id)} className="strike-button-card" />
                        <img alt="card" src={getCardSource(this.props.strikes[j].card2Id)} className="strike-button-card" />
                        <img alt="card"  src={getCardSource(this.props.strikes[j].card3Id)} className="strike-button-card" />
                    </div>);
            }

            strikes1Col.push(<div>{tmp}</div>);

            tmp = [];

            for (let j = colLength; j < this.props.strikes.length; j++) {
                tmp.push(
                    <div key={this.props.strikes[j].round}>
                        kör:{this.props.strikes[j].round}
                        <img alt="card" src={getCardSource(this.props.strikes[j].card1Id)} className="strike-button-card" />
                        <img alt="card" src={getCardSource(this.props.strikes[j].card2Id)} className="strike-button-card" />
                        <img alt="card" src={getCardSource(this.props.strikes[j].card3Id)} className="strike-button-card" />
                    </div>);
            }

            strikes2Col.push(<div>{tmp}</div>);

        } else {

            let tmp = [];

            for (let j = 0; j < this.props.strikes.length; j++) {
                tmp.push(
                    <div key={this.props.strikes[j].round}>
                        kör:{this.props.strikes[j].round}
                        <img alt="card" src={getCardSource(this.props.strikes[j].card1Id)} className="strike-button-card" />
                        <img alt="card" src={getCardSource(this.props.strikes[j].card2Id)} className="strike-button-card" />
                        <img alt="card" src={getCardSource(this.props.strikes[j].card3Id)} className="strike-button-card" />
                    </div>);
            }

            strikes1Col.push(<div>{tmp}</div>);

        }

        return (
            <div>
                <Button onClick={() => this.handleShow(this.state.isShow)} disabled={this.isShowStrikeButtonDisabled()} >ütések</Button>
                <Modal show={this.state.isShow}
                    size="lg">
                    <Modal.Body>
                        <div className="flex-row">
                            <div>{strikes1Col}</div>
                            <div>{strikes2Col}</div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={() => this.handleShow(this.state.isShow)}>Bezár</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }

    handleShow(lastState: boolean) {
        this.setState({ isShow: !lastState });
    }

    isShowStrikeButtonDisabled() {
        if (this.props.strikes.length > 0)
            return false;

        return true;
    }
}