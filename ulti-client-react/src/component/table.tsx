import * as React from 'react'
import { CardHandler } from '../cardHandler';

export default class Table extends React.Component {

    render(){
        return (
            <div className="table">
                <div className = "playground">
                    <img src={CardHandler.getCardSource(0)}/>
                </div>
                <div className = "talon"></div>
                <div className = "hand"></div>
                <div className = "strikes"></div>
            </div>
        );
    }
}