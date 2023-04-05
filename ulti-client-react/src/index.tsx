import ReactDOM from 'react-dom/client';
import App from "./functions/App";
import 'bootstrap/dist/css/bootstrap.min.css';
import './resources/css/ulti.css';

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
  );
  root.render(
      <App/>
  );
