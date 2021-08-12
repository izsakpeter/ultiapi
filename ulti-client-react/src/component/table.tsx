import * as React from 'react'

export default class Table extends React.Component {

    render(){
        return (
            <div className="table">
                <div className = "playground"></div>
                <div className = "talon"></div>
                <div className = "hand"></div>
                <div className = "strikes"></div>
            </div>
        );
    }
}