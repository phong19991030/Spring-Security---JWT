import "./App.css";
import Message from "./components/Message";
import Login from "./components/Login";
import { Navigate, useRoutes } from "react-router-dom";
import Profile from "./components/Profile";

function App() {
  let router = useRoutes([
    { path: "/", element: <Login /> },
    { path: "/chat", element: <Message /> },
    { path: "/profile", element: <Profile /> },
  ]);

  return <div className="App">{router}</div>;
}

export default App;
