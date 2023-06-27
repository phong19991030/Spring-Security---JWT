import React, { useEffect, useState } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import { MessageModel } from "../model/MessageModel";
import { Cookies } from "react-cookie";

export default function Socket() {
  const [message, setMessage] = useState<MessageModel>(
    new MessageModel("", "")
  );

  const [list, setList] = useState<any>([]);

  const [ws, setWs] = useState<any>(
    new SockJS("http://localhost:8080/stomp-endpoint")
  );

  const [stompClient, setStompClient] = useState<any>(Stomp.over(ws));

  useEffect(() => {
    connect();
  }, []);

  const connect = () => {
    stompClient.connect({}, () => {
      init();
      stompClient.subscribe(
        "/topic/getList",
        (msg: any) => {
          // get the message
          setList(JSON.parse(msg.body));
          console.log(JSON.parse(msg.body));
        },
        (err: any) => {
          console.log(err);
        }
      );
    });
  };

  function init() {
    message && stompClient.send("/app/init", {}, {});
  }

  function add() {
    message && stompClient.send("/app/add", {}, JSON.stringify(message));
    setMessage({ ...message, content: "" });
  }

  const handleChangeField = (event: any) => {
    let field = event.target.name;
    let value = event.target.value;
    setMessage({ ...message, [field]: value });
  };

  const remove = (data: MessageModel) => {
    message && stompClient.send("/app/remove", {}, JSON.stringify(data));
  };

  return (
    <>
      <div>Socket</div>
      <input
        value={message.userName}
        name="name"
        type="text"
        placeholder="name"
        onChange={handleChangeField}
      />
      <input
        value={message.content}
        name="content"
        type="text"
        placeholder="content"
        onChange={handleChangeField}
      />
      <button onClick={add}>send</button>
      <ul>
        {list &&
          list.map((element: MessageModel) => (
            <li>
              {element.userName} - {element.content}
              <button
                hidden={message.userName !== element.userName}
                onClick={() => {
                  remove(element);
                }}
              >
                remove
              </button>
            </li>
          ))}
      </ul>
    </>
  );
}
