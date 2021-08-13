import * as React from 'react'
import Header from './header';
import NavPanel from './navpanel';
import Table from './table';

export class App extends React.Component {
    render() {
        return (
            <div>
                <Header></Header>
                <NavPanel></NavPanel>
                <Table></Table>
            </div>
        );
    }
}